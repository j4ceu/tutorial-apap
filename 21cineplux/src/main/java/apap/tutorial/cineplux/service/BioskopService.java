package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.FilmModel;

import java.util.List;

public interface BioskopService {
    void addBioskop(BioskopModel bioskop);

    void updateBioskop(BioskopModel bioskop);

    void deleteBioskop(BioskopModel bioskop);

    List<BioskopModel> getBioskopList();

    BioskopModel getBioskopByNoBioskop(Long noBioskop);

    boolean cekWaktuBuka (BioskopModel bioskop);

    List<FilmModel> filmListTemp(FilmModel film);

    List<FilmModel> getFilmListTemp();

    void clearFilmListTemp();


}
