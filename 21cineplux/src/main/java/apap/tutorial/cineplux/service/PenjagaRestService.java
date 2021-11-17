package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.PenjagaModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PenjagaRestService {
    PenjagaModel createPenjaga(PenjagaModel penjaga);
    List<PenjagaModel> retrievePenjagaList();
    PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga);
    PenjagaModel updatePenjaga(Long noPenjaga, PenjagaModel penjagaUpdate);
    void deletePenjaga(Long noPenjaga);
    PenjagaModel getUmur (Long noPenjaga);
    List<PenjagaModel> retrievePenjagaListByJenisKelamin(Integer jenisKelamin);

}
