package group.sneakers.sneakers.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.sneakers.sneakers.model.products.Image;
import group.sneakers.sneakers.repository.products.ImageRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ImagesService imagesService;

	public List<Image> findAll() {
		return imageRepository.findAll();
	}

	public Image findById(Integer id) {
		return imageRepository.findById(id).orElse(null);
	}

	public Image save(Image i) {
		return imageRepository.save(i);
	}

	public Image partialUpdate(Image i) {
		Image existing = imageRepository.findById(i.getId()).orElse(null);
		if (existing != null) {
			if (i.getUrl() != null) existing.setUrl(i.getUrl());
			if (i.getDescription() != null) existing.setDescription(i.getDescription());
			return imageRepository.save(existing);
		}
		return null;
	}

	public void deleteById(Integer id) {
		List<group.sneakers.sneakers.model.products.Images> list = imagesService.findAll();
		for (group.sneakers.sneakers.model.products.Images im : list) {
			if (im.getImage() != null && im.getImage().getId().equals(id)) {
			}
		}
		imageRepository.deleteById(id);
	}

}
