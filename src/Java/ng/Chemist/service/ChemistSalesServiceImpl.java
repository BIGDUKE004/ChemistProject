package Java.ng.Chemist.service;

import Java.ng.Chemist.Data.model.DispensedDrugsRecord;
import Java.ng.Chemist.Data.model.Drug;
import Java.ng.Chemist.Data.model.User;
import Java.ng.Chemist.Data.repositories.DispensedDrugsRecordRepository;
import Java.ng.Chemist.Data.repositories.DrugRepository;
import Java.ng.Chemist.Data.repositories.UserRepository;
import Java.ng.Chemist.dtos.request.chemistSalesServiceRequest.sellDrugRequest;
import Java.ng.Chemist.dtos.response.chemistSalesServiceResponse.sellDrugResponse;
import Java.ng.Chemist.exceptions.ChemistSalesManagementExceptions.DrugNotFoundException;
import Java.ng.Chemist.exceptions.ChemistSalesManagementExceptions.UserNotLoggedInException;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class ChemistSalesServiceImpl implements ChemistSalesService {
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DispensedDrugsRecordRepository dispensedDrugsRecordRepository;

    @Override
    public sellDrugResponse sellDrug(sellDrugRequest request) {
        ArrayList<String> dispenseDrugs = new ArrayList<>();

        BigDecimal total = BigDecimal.valueOf(0);

        int totalQuantity = 0;

        for(int counter = 0; counter < request.getDrugs().size(); counter++){

            boolean check = this.drugRepository.existsByBrandNameAndDosage(request.getDrugs().get(counter).getDrugName(), request.getDrugs().get(counter).getDosage());

            Optional<Drug> quantity =
                    drugRepository.findById(request.getDrugs().get(counter).getId());

            if(check && quantity.isPresent() && quantity.get().getQuantityInStock() > request.getDrugs().get(counter).getQuantity()){

                BigDecimal lineTotal;

                lineTotal = this.drugRepository.findById(request.getDrugs().get(counter).getId()).get().getPrice().multiply(BigDecimal.valueOf(request.getDrugs().get(counter).getQuantity()));

                String drugMessage = "%d x %s x %s = %s".formatted(request.getDrugs().get(counter).getQuantity(), request.getDrugs().get(counter).getDrugName(), request.getDrugs().get(counter).getDosage(), lineTotal);

                dispenseDrugs.add(drugMessage);

                total = total.add(lineTotal);

                totalQuantity += request.getDrugs().get(counter).getQuantity();

                this.drugRepository.findByBrandName(request.getDrugs().get(counter).getDrugName()).setQuantityInStock(this.drugRepository.findByBrandName(request.getDrugs().get(counter).getDrugName()).getQuantityInStock() - request.getDrugs().get(counter).getQuantity());

            } else {
                throw new DrugNotFoundException("Could not find drug");
            }

        }
        Optional<User> user = this.userRepository.findByUserName(request.getName());
        if (!user.get().isLoggedIn()) {
            throw new UserNotLoggedInException("Please log in your account");
        }
        int id = 101;
        DispensedDrugsRecord record = new DispensedDrugsRecord();
        record.setSaleId(id);
        record.setDateAndTime(LocalDateTime.now());
        record.setUser(user.get().getUserName());
        record.setDrugs(dispenseDrugs);
        record.setQuantitySold(totalQuantity);
        record.setAmount(total);

        dispensedDrugsRecordRepository.save(record);

        sellDrugResponse salesResponse = new sellDrugResponse();
        salesResponse.setRecord(record);
        id++;
        return salesResponse;
    }
}
