package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
public class BioskopController {

    @Qualifier("bioskopServiceImpl")
    @Autowired
    private BioskopService bioskopService;

    @GetMapping("/bioskop/add")
    public String addBioskop(Model model) {
        model.addAttribute("bioskop", new BioskopModel());
        return "form-add-bioskop";
    }

    @PostMapping("/bioskop/add")
    public String addBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        bioskopService.addBioskop(bioskop);
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
            model.addAttribute("buka", buka);
            model.addAttribute("delete", delete);

            return "view-bioskop";
        } else {
            model.addAttribute("noBioskop", noBioskop);
            return "notFoundId";
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


    }

}
