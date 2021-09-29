package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;

public interface PenjagaService {
    void addPenjaga(PenjagaModel penjaga);

    void updatePenjaga(PenjagaModel penjaga);

    void deletePenjaga(PenjagaModel penjaga);

    PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga);
}
