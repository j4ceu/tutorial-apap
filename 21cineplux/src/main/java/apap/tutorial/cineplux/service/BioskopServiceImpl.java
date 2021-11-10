package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.FilmModel;
import apap.tutorial.cineplux.repository.BioskopDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class BioskopServiceImpl implements BioskopService {
    ArrayList<FilmModel> listFilm = new ArrayList<FilmModel>();
    @Autowired
    BioskopDB bioskopDB;

    @Override
    public void addBioskop(BioskopModel bioskop){
        bioskopDB.save(bioskop);
    }

    @Override
    public void updateBioskop(BioskopModel bioskop){
        bioskopDB.save(bioskop);
    }

    @Override
    public void deleteBioskop(BioskopModel bioskop){
        bioskopDB.delete(bioskop);
    }

    @Override
    public List<BioskopModel> getBioskopList(){
        return bioskopDB.findAll(Sort.by("namaBioskop").ascending());
    }

    @Override
    public List<FilmModel> filmListTemp(FilmModel film){
        listFilm.add(film);
        return listFilm;
    }

    @Override
    public List<FilmModel> getFilmListTemp(){
        return listFilm;
    }

    @Override
    public void clearFilmListTemp() {
        listFilm.clear();
    }

    @Override
    public BioskopModel getBioskopByNoBioskop(Long noBioskop){
        Optional<BioskopModel> bioskop = bioskopDB.findByNoBioskop(noBioskop);
        if(bioskop.isPresent()){
            return bioskop.get();
        }
        return null;
    }

    @Override
    public boolean cekWaktuBuka(BioskopModel bioskop) {
        LocalTime localTime = LocalTime.now();
        if ((bioskop.getWaktuTutup().isAfter(localTime)) & (bioskop.getWaktuBuka().isBefore(localTime))){
            return true;
        } else {
            return false;
        }

    }
}