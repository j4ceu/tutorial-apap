package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import apap.tutorial.cineplux.service.PenjagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class PenjagaController {
    @Qualifier("penjagaServiceImpl")
    @Autowired
    PenjagaService penjagaService;

    @Qualifier("bioskopServiceImpl")
    @Autowired
    BioskopService bioskopService;

    @GetMapping("/penjaga/add/{noBioskop}")
    public String addPenjagaForm(
            @PathVariable Long noBioskop,
            Model model
    ) {
        PenjagaModel penjaga = new PenjagaModel();
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        penjaga.setBioskop(bioskop);
        model.addAttribute("penjaga",penjaga);
        return "form-add-penjaga";
    }

    @PostMapping("/penjaga/add")
    public String addPenjagaSubmit(
            @ModelAttribute PenjagaModel penjaga,
            Model model
    ) {
        penjagaService.addPenjaga(penjaga);
        model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
        model.addAttribute("namaPenjaga",penjaga.getNamaPenjaga());
        return "add-penjaga";
    }

    @GetMapping("/penjaga/update/{noPenjaga}")
    public String updatePenjagaForm(
            @PathVariable Long noPenjaga,
            Model model
    ) {

        PenjagaModel penjaga = penjagaService.getPenjagaByNoPenjaga(noPenjaga);
        if (penjaga != null) {
            model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
            model.addAttribute("penjaga", penjaga);
        } else {
            model.addAttribute("noPenjaga", noPenjaga);
            return "notFoundNoPenjaga";
        }
        return "form-update-penjaga";
    }

    @PostMapping("/penjaga/update")
    public String updatePenjagaSubmit(
            @ModelAttribute PenjagaModel penjaga,
            Model model
    ) {
        // Button Update Keliatan Sesuai Jam
        LocalTime localTime = LocalTime.now();
        boolean buka = bioskopService.cekWaktuBuka(penjaga.getBioskop());

        if (buka){
            model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
            return "bioskopBuka";
        } else {
            penjagaService.updatePenjaga(penjaga);
            model.addAttribute("noPenjaga", penjaga.getNoPenjaga());
            model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
            return "update-bioskop";
        }

    }

    @GetMapping("/penjaga/delete/{noPenjaga}")
    public String deletePenjaga(
            @PathVariable Long noPenjaga,
            Model model
    ) {

        PenjagaModel penjaga = penjagaService.getPenjagaByNoPenjaga(noPenjaga);
        if (penjaga != null){

            boolean buka = bioskopService.cekWaktuBuka(penjaga.getBioskop());
            if (buka){
                model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
                return "bioskopBuka";
            } else {
                penjagaService.deletePenjaga(penjaga);
                model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
                model.addAttribute("penjaga", penjaga);
                return "deletePenjagaSucces";
            }
        } else {
            model.addAttribute("noPenjaga", noPenjaga);
            return "notFoundNoPenjaga";
        }
    }



}