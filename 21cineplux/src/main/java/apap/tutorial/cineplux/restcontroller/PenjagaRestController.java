package apap.tutorial.cineplux.restcontroller;

import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.PenjagaRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value="/api/v1")
public class PenjagaRestController {
    @Autowired
    private PenjagaRestService penjagaRestService;

    @PostMapping("/penjaga")
    private ResponseEntity createPenjaga(@Valid @RequestBody PenjagaModel penjaga, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type of missing field."
            );
        } else {
            penjagaRestService.createPenjaga(penjaga);
            return ResponseEntity.ok("Create Penjaga Success!");
        }
    }

    @GetMapping(value="/penjaga/{noPenjaga}")
    private PenjagaModel retrievePenjaga(@PathVariable("noPenjaga") Long noPenjaga){
        try {
            return penjagaRestService.getPenjagaByNoPenjaga(noPenjaga);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Penjaga " + String.valueOf(noPenjaga) + " Not Found."
            );
        }
    }

    @DeleteMapping(value="/penjaga/{noPenjaga}")
    private ResponseEntity deletePenjaga(@PathVariable("noPenjaga") Long noPenjaga){
        try {
            penjagaRestService.deletePenjaga(noPenjaga);
            return ResponseEntity.ok("Penjaga Has Been Deleted!");
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Penjaga with No Penjaga " + String.valueOf(noPenjaga) + " Not Found."
            );
        } catch (UnsupportedOperationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bioskop is still open!"
            );
        }
    }

    @PutMapping(value="/penjaga/{noPenjaga}")
    private ResponseEntity updatePenjaga(@PathVariable("noPenjaga") Long noPenjaga, @RequestBody PenjagaModel penjaga) {
        try {
            penjagaRestService.updatePenjaga(noPenjaga, penjaga);
            return ResponseEntity.ok("Update Penjaga Success!");
        } catch (NoSuchElementException e) {
            if(e.getMessage() != null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Penjaga with No Penjaga " + String.valueOf(noPenjaga) + " Not Found.");
        } catch (UnsupportedOperationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bioskop is still open!"
            );
        }
    }

    @GetMapping(value="/list-penjaga")
    private List<PenjagaModel> retrievePenjagaList() {
        return penjagaRestService.retrievePenjagaList();
    }

    @GetMapping(value="penjaga/umur/{noPenjaga}")
    private PenjagaModel getStatus(@PathVariable("noPenjaga") Long noPenjaga){
        try {
            return penjagaRestService.getUmur(noPenjaga);
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Penjaga with No Penjaga " + String.valueOf(noPenjaga) + " Not Found.");
        }catch (UnsupportedOperationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bioskop is still open!"
            );
        }

    }

    @GetMapping(value="penjaga/find/{jenisKelamin}")
    private List<PenjagaModel> getPenjagaByJenisKelamin(@PathVariable("jenisKelamin") Integer jenisKelamin) {
        return penjagaRestService.retrievePenjagaListByJenisKelamin(jenisKelamin);
    }




}
