package sa.aref.controller.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.aref.entity.offer.Offer;
import sa.aref.service.offer.OfferService;

import java.util.List;

@RestController
@RequestMapping(("/offer"))
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable("id") Integer id) {
        Offer offer = offerService.getOfferById(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Offer> saveOffer(@RequestBody Offer offer) {
        Offer savedOffer = offerService.saveOffer(offer);
        return new ResponseEntity<>(savedOffer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOfferById(@PathVariable("id") Integer id) {
        offerService.deleteOfferById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
