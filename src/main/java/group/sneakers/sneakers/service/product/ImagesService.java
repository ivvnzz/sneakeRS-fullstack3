package group.sneakers.sneakers.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Images;
import group.sneakers.sneakers.repository.products.ImagesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ImagesService {

	@Autowired
	private ImagesRepository imagesRepository;

	public List<Images> findAll() {
		return imagesRepository.findAll();
	}

	public Images findById(Long id) {
		return imagesRepository.findById(id).orElse(null);
	}

	public Images save(Images i) {
		return imagesRepository.save(i);
	}

	public Images partialUpdate(Images i) {
		Images existing = imagesRepository.findById(i.getId()).orElse(null);
		if (existing != null) {
			if (i.getProduct() != null) existing.setProduct(i.getProduct());
			if (i.getImage() != null) existing.setImage(i.getImage());
			return imagesRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Long id) {
		imagesRepository.deleteById(id);
	}

	public void deleteByProductId(Integer productId) {
		List<Images> list = imagesRepository.findAll();
		for (Images im : list) {
			if (im.getProduct() != null && im.getProduct().getId().equals(productId)) {
				imagesRepository.deleteById(im.getId());
			}
		}
	}

	public List<Images> findByProductId(Integer productId) {
		List<Images> out = new ArrayList<>();
		List<Images> list = imagesRepository.findAll();
		for (Images im : list) {
			if (im.getProduct() != null && im.getProduct().getId().equals(productId)) {
				out.add(im);
			}
		}
		return out;
	}

	public void deleteByImageId(Integer imageId) {
		List<Images> list = imagesRepository.findAll();
		for (Images im : list) {
			if (im.getImage() != null && im.getImage().getId().equals(imageId)) {
				imagesRepository.deleteById(im.getId());
			}
		}
	}

}
