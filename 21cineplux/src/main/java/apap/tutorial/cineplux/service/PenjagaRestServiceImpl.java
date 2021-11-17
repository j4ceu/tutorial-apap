package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.BioskopDB;
import apap.tutorial.cineplux.repository.PenjagaDB;
import apap.tutorial.cineplux.rest.BioskopDetail;
import apap.tutorial.cineplux.rest.Setting;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.NotSupportedException;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class PenjagaRestServiceImpl implements PenjagaRestService {
    private final WebClient webClient;

    @Autowired
    private PenjagaDB penjagaDB;

    @Autowired
    BioskopDB bioskopDB;

    @Override
    public PenjagaModel createPenjaga(PenjagaModel penjaga) {
        return penjagaDB.save(penjaga);
    }

    @Override
    public List<PenjagaModel> retrievePenjagaList() {
        return penjagaDB.findAll();
    }

    @Override
    public PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga){
        Optional<PenjagaModel> penjaga = penjagaDB.findByNoPenjaga(noPenjaga);
        if(penjaga.isPresent()){
            return penjaga.get();
        }
        throw  new NoSuchElementException();
    }

    @Override
    public PenjagaModel updatePenjaga(Long noPenjaga, PenjagaModel penjagaUpdate) {
        LocalTime now = LocalTime.now();
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        BioskopModel bioskop = bioskopDB.findByNoBioskop(penjaga.getBioskop().getNoBioskop()).get();
        if((now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup()))) {
            penjaga.setNamaPenjaga(penjagaUpdate.getNamaPenjaga());
            penjaga.setJenisKelamin(penjagaUpdate.getJenisKelamin());
            Optional<BioskopModel> newBioskop = bioskopDB.findByNoBioskop(penjagaUpdate.getBioskop().getNoBioskop());
            if (newBioskop.isPresent()){
                penjaga.setBioskop(penjagaUpdate.getBioskop());
            } else {
                throw  new NoSuchElementException("No Bioskop Not Found!");
            }

            return penjagaDB.save(penjaga);
        } else {
            throw new UnsupportedOperationException("Bioskop is still open!");
        }
    }

    @Override
    public void deletePenjaga(Long noPenjaga){
        LocalTime now = LocalTime.now();
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        BioskopModel bioskop = bioskopDB.findByNoBioskop(penjaga.getBioskop().getNoBioskop()).get();
        if((now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup()))) {
            penjagaDB.delete(penjaga);
        } else {
            throw new UnsupportedOperationException("Bioskop is still open!");
        }
    }

    public PenjagaRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.umurUrl).build();
    }

    @Override
    public PenjagaModel getUmur(Long noPenjaga){
        LocalTime now = LocalTime.now();
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        BioskopModel bioskop = bioskopDB.findByNoBioskop(penjaga.getBioskop().getNoBioskop()).get();
        if((now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup()))) {
            String namaPenjaga = penjaga.getNamaPenjaga();
            String[] firstName = namaPenjaga.split(" ");

            String getStringApi = this.webClient.get().uri("/?name=" + firstName[0])
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(getStringApi, Map.class);


            getStringApi = getStringApi.replace("{", "");
            getStringApi = getStringApi.replace("}", "");

            String[] splitStringApi = getStringApi.split(",");
            String[] getAge = splitStringApi[1].split(":");
            penjaga.setUmur(getAge[1]);

            return penjagaDB.save(penjaga);
        } else {
            throw new UnsupportedOperationException("Bioskop is still open!");
        }
    }

    @Override
    public List<PenjagaModel> retrievePenjagaListByJenisKelamin(Integer jenisKelamin) {
        return penjagaDB.findAllByJenisKelamin(jenisKelamin);
    }


}
