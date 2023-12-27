package ma.emsi.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.budget.model.Compte;
import ma.emsi.budget.repository.CompteRepository;
import ma.emsi.budget.service.CompteService;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

	@Autowired
	private CompteRepository compteRepository;

	@Override
	public Compte add(Compte e) {
		return compteRepository.save(e);
	}

	@Override
	public List<Compte> getAll() {
		return compteRepository.findAll();
	}

	@Override
	public Compte getById(int id) {
		return compteRepository.findCompteById(id);
	}

	@Override
	public void update(Compte e) {
		Compte c = getById(e.getId());
		if (c != null) {
			c.setNom(e.getNom());
			c.setSolde(e.getSolde());
			c.setUtilisateur(e.getUtilisateur());
			compteRepository.save(c);
		}
		System.out.println("Old solde " + e.getSolde() + "\n");
		System.out.println("New solde " + c.getSolde());
	}

	@Override
	public void delete(int id) {
		compteRepository.deleteById(id);
	}

	@Override
	public List<Compte> findComptesByUser(int id) {
		return compteRepository.findComptesByUser(id);
	}

}
