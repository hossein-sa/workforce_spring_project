package sa.aref.service.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.aref.entity.offer.Offer;
import sa.aref.repository.offer.OfferRepository;

import java.util.List;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Offer getOfferById(Integer id) {
        return offerRepository.findById(id).orElse(null);
    }

    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public void deleteOfferById(Integer id) {
        offerRepository.deleteById(id);
    }
}
