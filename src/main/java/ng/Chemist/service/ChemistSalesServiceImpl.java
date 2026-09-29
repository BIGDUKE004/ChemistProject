package ng.Chemist.service;

import ng.Chemist.Data.model.DispensedDrugsRecord;
import ng.Chemist.Data.model.Drug;
import ng.Chemist.Data.model.User;
import ng.Chemist.Data.repositories.DispensedDrugsRecordRepository;
import ng.Chemist.Data.repositories.DrugRepository;
import ng.Chemist.Data.repositories.UserRepository;
import ng.Chemist.dtos.request.chemistSalesServiceRequest.sellDrugRequest;
import ng.Chemist.dtos.response.chemistSalesServiceResponse.sellDrugResponse;
import ng.Chemist.exceptions.ChemistSalesManagementExceptions.DrugNotFoundException;
import ng.Chemist.exceptions.ChemistSalesManagementExceptions.InsufficientStockException;
import ng.Chemist.exceptions.ChemistSalesManagementExceptions.UserNotLoggedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class ChemistSalesServiceImpl implements ChemistSalesService {
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DispensedDrugsRecordRepository dispensedDrugsRecordRepository;

    @Override
    @Transactional
    public sellDrugResponse sellDrug(sellDrugRequest request) {
        ArrayList<String> dispenseDrugs = new ArrayList<>();

        BigDecimal total = BigDecimal.valueOf(0);

        int totalQuantity = 0;

        Optional<User> user = this.userRepository.findByUserName(request.getName());
        if (user.isEmpty()) {
            throw new UserNotLoggedInException("User not found");
        }
        if (!user.get().isLoggedIn()) {
            throw new UserNotLoggedInException("Please log in your account");
        }

        for (int counter = 0; counter < request.getDrugs().size(); counter++) {

            Optional<Drug> foundDrug =
                    drugRepository.findById(request.getDrugs().get(counter).getId());

            if (foundDrug.isEmpty()
                    || !foundDrug.get().getBrandName().equals(request.getDrugs().get(counter).getDrugName())
                    || !foundDrug.get().getDosage().equals(request.getDrugs().get(counter).getDosage())) {
                throw new DrugNotFoundException("Could not find drug");
            }

            Drug drug = foundDrug.get();

            if (drug.getQuantityInStock() < request.getDrugs().get(counter).getQuantity()) {
                throw new InsufficientStockException("Not enough stock for " + request.getDrugs().get(counter).getDrugName());
            }

            BigDecimal lineTotal = drug.getPrice().multiply(BigDecimal.valueOf(request.getDrugs().get(counter).getQuantity()));

            String drugMessage = "%d x %s x %s = %s".formatted(request.getDrugs().get(counter).getQuantity(), request.getDrugs().get(counter).getDrugName(), request.getDrugs().get(counter).getDosage(), lineTotal);

            dispenseDrugs.add(drugMessage);

            total = total.add(lineTotal);

            totalQuantity += request.getDrugs().get(counter).getQuantity();

            drug.setQuantityInStock(drug.getQuantityInStock() - request.getDrugs().get(counter).getQuantity());
            this.drugRepository.save(drug);
        }

        DispensedDrugsRecord record = new DispensedDrugsRecord();
        record.setDateAndTime(LocalDateTime.now());
        record.setUser(user.get().getUserName());
        record.setDrugs(dispenseDrugs);
        record.setQuantitySold(totalQuantity);
        record.setAmount(total);

        dispensedDrugsRecordRepository.save(record);

        sellDrugResponse salesResponse = new sellDrugResponse();
        salesResponse.setRecord(record);
        return salesResponse;
    }

}