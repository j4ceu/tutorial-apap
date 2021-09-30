package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.FilmModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import apap.tutorial.cineplux.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
public class BioskopController {
int counter = 0;

    @Qualifier("bioskopServiceImpl")
    @Autowired
    private BioskopService bioskopService;

    @Qualifier("filmServiceImpl")
    @Autowired
    FilmService filmService;


    @GetMapping("/bioskop/add")
    public String addBioskop(Model model) {
        List<FilmModel> listFilm = filmService.getListFilm();
        model.addAttribute("bioskop", new BioskopModel());
        model.addAttribute("listFilm", listFilm);

        return "form-add-bioskop";
    }

    @RequestMapping(value = "/bioskop/add", method = RequestMethod.POST , params="addfilm")
    public String addBioskopTemp(
            @ModelAttribute BioskopModel bioskop,
            @RequestParam(name = "film_bioskop", required = false) Long filmBioskop,
            Model model) {
        counter++;

        List<FilmModel> listFilm = filmService.getListFilm();




        if(filmBioskop != null){
        bioskopService.filmListTemp(filmService.getFilmByNoFilm(filmBioskop));
        }

        List<FilmModel> filmTemp = bioskopService.getFilmListTemp();
        if(filmTemp.size() != 0 ){
            model.addAttribute("filmTemp", filmTemp);
        }


        model.addAttribute("adaFilm", true);
        model.addAttribute("bioskop", bioskop);
        model.addAttribute("listFilm", listFilm);
        model.addAttribute("counter", counter);



        return "form-add-bioskop";
    }

    @RequestMapping(value = "/bioskop/add", method = RequestMethod.POST , params="deletefilm")
    public String addBioskopTempDelete(
            @ModelAttribute BioskopModel bioskop,
            @RequestParam(name = "film_bioskop", required = false) Long filmBioskop,
            Model model) {
        counter--;

        List<FilmModel> listFilm = filmService.getListFilm();





        List<FilmModel> filmTemp = bioskopService.getFilmListTemp();
        if(filmTemp.size() != 0 ){
            model.addAttribute("filmTemp", filmTemp);
        }

        if( counter <= 0) {
            model.addAttribute("adaFilm", false);
        } else {
            model.addAttribute("adaFilm", true);
        }

        model.addAttribute("bioskop", bioskop);
        model.addAttribute("listFilm", listFilm);
        model.addAttribute("counter", counter);



        return "form-add-bioskop";
    }

    @RequestMapping(value = "/bioskop/add", method = RequestMethod.POST , params="add")
    public String addBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        System.out.println(bioskop.getListFilm());

        bioskopService.addBioskop(bioskop);
        counter = 0;

        model.addAttribute("noBioskop", bioskop.getNamaBioskop());
        return "add-bioskop";
    }

    @GetMapping("/bioskop/viewall")
    public String listBioskop(Model model) {
        List<BioskopModel> listBioskop = bioskopService.getBioskopList();
        model.addAttribute("listBioskop", listBioskop);
        return "viewall-bioskop";
    }

    @GetMapping("/bioskop/view")
    public String viewDetailBioskop(
            @RequestParam(value = "noBioskop") Long noBioskop,
            Model model
    ) {
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);


        if (bioskop != null) {
            List<FilmModel> listFilm = bioskop.getListFilm();
            List<PenjagaModel> listPenjaga = bioskop.getListPenjaga();
            // Button Update Keliatan Sesuai Jam
            LocalTime localTime = LocalTime.now();
            boolean buka = bioskopService.cekWaktuBuka(bioskop);

            // Cek Penjaga
            boolean tidakAdaPenjaga = bioskop.getListPenjaga().isEmpty();

            // Delete Mode
            boolean delete = true;

            if (buka == false && tidakAdaPenjaga == true) {
                delete = true;
            } else {
                delete = false;
            }


            model.addAttribute("bioskop", bioskop);
            model.addAttribute("listPenjaga", listPenjaga);
            model.addAttribute("listFilm", listFilm);
            model.addAttribute("buka", buka);
            model.addAttribute("delete", delete);

            return "view-bioskop";
        } else {
            model.addAttribute("noBioskop", noBioskop);
            return "notFoundNoBioskop";
        }
    }

    @GetMapping("/bioskop/update/{noBioskop}")
    public String updateBioskopForm(
            @PathVariable Long noBioskop,
            Model model
    ) {
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        model.addAttribute("bioskop", bioskop);
        return "form-update-bioskop";
    }

    @PostMapping("/bioskop/update")
    public String updateBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        bioskopService.updateBioskop(bioskop);
        model.addAttribute("noBioskop", bioskop.getNoBioskop());
        return "update-bioskop";
    }

    @GetMapping("/bioskop/delete/{noBioskop}")
    public String deleteBioskop(
            @PathVariable Long noBioskop,
            Model model
    ) {

        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);

        if (bioskop != null) {

            boolean buka = bioskopService.cekWaktuBuka(bioskop);

            // Cek Penjaga
            boolean tidakAdaPenjaga = bioskop.getListPenjaga().isEmpty();

            // Delete Mode
            boolean delete = true;

            if (buka == false && tidakAdaPenjaga == true) {
                bioskopService.deleteBioskop(bioskop);
                model.addAttribute("noBioskop", noBioskop);
                return "deleteSuccess";

            } else {
                model.addAttribute("noBioskop", noBioskop);
                return "cantDeleteBioskop";
            }
        } else {
            model.addAttribute("noBioskop", noBioskop);
            return "notFoundNoBioskop";
        }

    }

}
