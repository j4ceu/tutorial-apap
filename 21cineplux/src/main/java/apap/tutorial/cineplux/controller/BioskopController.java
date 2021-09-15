package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.service.BioskopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BioskopController {
    @Autowired
    private BioskopService bioskopService;

    //Routing URL yang diinginkan
    @RequestMapping("/bioskop/add")
    public String addBioskop(
            // Request parameter yang ingin digunakan
            @RequestParam(value = "idBioskop", required = true) String idBioskop,
            @RequestParam(value = "namaBioskop", required = true) String namaBioskop,
            @RequestParam(value = "alamat", required = true) String alamat,
            @RequestParam(value = "noTelepon", required = true) String noTelepon,
            @RequestParam(value = "jumlahStudio", required = true) int jumlahStudio,
            Model model
    ) {
        //Membuat objek Bioskop Model
        BioskopModel bioskopModel = new BioskopModel(idBioskop, namaBioskop, alamat, noTelepon, jumlahStudio);

        //Menambahkan objek BioskopModel kedalam service
        bioskopService.addBioskop(bioskopModel);

        //Add variabel id bioskop ke "idBioskop" untuk dirender ke dalam thymeleaf
        model.addAttribute("idBioskop", idBioskop);

        //Return view template yang digunakan
        return "add-bioskop.html";
    }

    @RequestMapping("/bioskop/viewall")
    public String listBioskop (Model model) {
        List<BioskopModel> listBioskop = bioskopService.getBioskopList();
        model.addAttribute("listBioskop", listBioskop);
        return "viewall-bioskop.html";
    }

    @RequestMapping("/bioskop/view")
    public String detailBioskop(
            @RequestParam(value = "idBioskop", required = true)String idBioskop,
            Model model
    ) {
        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
        model.addAttribute("bioskop", bioskopModel);
        return "view-bioskop.html";
    }

    @GetMapping("bioskop/view/id-bioskop/{idBioskop}")
    public String viewBioskop(
            @PathVariable(value = "idBioskop", required = true)String idBioskop,
            Model model
    ) {

            BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
            if (bioskopModel != null) {
                model.addAttribute("bioskop", bioskopModel);
                return "view-bioskop.html";
            }
            else {
                model.addAttribute("idBioskop", idBioskop);
                return "notFoundId.html";
            }

    }

    @GetMapping("bioskop/update/id-bioskop/{idBioskop}/jumlah-studio/{jumlahStudio}")
    public String updateJumlahStudio(
            @PathVariable(value = "idBioskop", required = true) String idBioskop,
            @PathVariable(value = "jumlahStudio", required = true) int jumlahStudio,
            Model model)
        {

            BioskopModel bioskop = bioskopService.getBioskopByIdBioskop(idBioskop);
            if (bioskop != null) {
            bioskop.setJumlahStudio(jumlahStudio);
            model.addAttribute("idBioskop", idBioskop);
            model.addAttribute("jumlahStudio", jumlahStudio);
            return "updateSuccess.html";
            } else {
                model.addAttribute("idBioskop", idBioskop);
                return "notFoundId.html";
            }
        }

    @GetMapping("bioskop/delete/id-bioskop/{idBioskop}")
    public String deleteBioskop(
            @PathVariable(value = "idBioskop", required = true) String idBioskop,
            Model model)
    {
        
        BioskopModel bioskop = bioskopService.getBioskopByIdBioskop(idBioskop);
        if (bioskop != null) {
            bioskopService.getBioskopList().remove(bioskop);
            model.addAttribute("idBioskop", idBioskop);
            return "deleteSuccess.html";

        } else  {
            model.addAttribute("idBioskop", idBioskop);
            return "notFoundId.html";
        }

    }
}
