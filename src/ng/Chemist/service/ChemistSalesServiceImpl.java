package ng.Chemist.service;

//import ng.Chemist.Data.model.DispenseDrug;
import ng.Chemist.Data.model.DispensedDrugsRecord;
//import ng.Chemist.Data.model.Drug;
import ng.Chemist.Data.model.User;
import ng.Chemist.Data.repositories.DispensedDrugsRecordRepositoryImpl;
import ng.Chemist.Data.repositories.DrugRepository;
import ng.Chemist.Data.repositories.UserRepository;
import ng.Chemist.Data.repositories.UserRepositoryImpl;
import ng.Chemist.dtos.request.chemistSalesServiceRequest.sellDrugRequest;
import ng.Chemist.dtos.response.chemistSalesServiceResponse.sellDrugResponse;
import ng.Chemist.exceptions.ChemistSalesManagementExceptions.DrugNotFoundException;
//import ng.Chemist.exceptions.ChemistSalesManagementExceptions.InsufficientStockException;
import ng.Chemist.exceptions.ChemistSalesManagementExceptions.UserNotLoggedInException;
//import ng.Chemist.util.authServiceUtil.Mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChemistSalesServiceImpl implements ChemistSalesService {
    private DrugRepository drugRepository;
    private UserRepository userRepository;

    public ChemistSalesServiceImpl(DrugRepository drugRepository, UserRepository userRepository){
        this.drugRepository = drugRepository;
        this.userRepository = userRepository;
    }

    @Override
    public sellDrugResponse sellDrug(sellDrugRequest request) {
        ArrayList<String> dispenseDrugs = new ArrayList<>();

        BigDecimal total = BigDecimal.valueOf(0);

        int totalQuantity = 0;

        for(int counter = 0; counter < request.getDrug().size(); counter++){

            boolean check = this.drugRepository.drugExistence(request.getDrug().get(counter).getDrug(), request.getDrug().get(counter).getDosage());

            if(check && this.drugRepository.findById(request.getDrug().get(counter).getId()).getQuantityInStock() > request.getDrug().get(counter).getQuantity()){

                BigDecimal lineTotal;

                lineTotal = this.drugRepository.findById(request.getDrug().get(counter).getId()).getPrice().multiply(BigDecimal.valueOf(request.getDrug().get(counter).getQuantity()));

                String drugMessage = "%d x %s x %s = %s".formatted(request.getDrug().get(counter).getQuantity(), request.getDrug().get(counter).getDrug(), request.getDrug().get(counter).getDosage(), lineTotal);

                dispenseDrugs.add(drugMessage);

                total = total.add(lineTotal);

                totalQuantity += request.getDrug().get(counter).getQuantity();

                this.drugRepository.viewDrugInformation(request.getDrug().get(counter).getDrug()).setQuantityInStock(this.drugRepository.viewDrugInformation(request.getDrug().get(counter).getDrug()).getQuantityInStock() - request.getDrug().get(counter).getQuantity());

            } else {
                throw new DrugNotFoundException("Could not find drug");
            }

        }
        User user = this.userRepository.findByName(request.getName());
        if (!user.isLoggedIn()) {
            throw new UserNotLoggedInException("Please log in your account");
        }
        int id = 101;
        DispensedDrugsRecord record = new DispensedDrugsRecord();
        record.setSaleId(id);
        record.setDateAndTime(LocalDateTime.now());
        record.setUser(user.getUserName());
        record.setDrug(dispenseDrugs);
        record.setQuantitySold(totalQuantity);
        record.setAmount(total);

        DispensedDrugsRecordRepositoryImpl dispensedDrugsRecordRepository = new DispensedDrugsRecordRepositoryImpl();
        dispensedDrugsRecordRepository.addCurrentSales(record);

        sellDrugResponse salesResponse = new sellDrugResponse();
        salesResponse.setMessage(record);
        id++;
        return salesResponse;
    }
}
