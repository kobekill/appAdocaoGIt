package br.com.animais.adocao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.animais.adocao.dao.AnimalDao;
import br.com.animais.adocao.dao.FotoDao;
import br.com.animais.adocao.dao.RacaDao;
import br.com.animais.adocao.model.Animal;
import br.com.animais.adocao.model.Foto;
import br.com.animais.adocao.model.Raca;
import br.com.animais.adocao.util.Constantes;

@ManagedBean
@ViewScoped
public class PesquisaAnimalAdocaoBean {

	private List<Animal> listaAnimal;

	private Animal animal;

	private AnimalDao animalDao;
	private List<Foto> listaFotos;

	private FotoDao fDao;

	public Animal animalSelecionado;
	private String nomeUsuario;

	private RacaDao racaDao;

	private List<Raca> racas;
	private List<String> listaTipo;
	private List<String> listaEstado;

	private List<String> selectRacas;

	public void inicializar() {
		fDao = new FotoDao();
		animalDao = new AnimalDao();
		animal = new Animal();
		if (animalSelecionado == null) {
			animalSelecionado = new Animal();
		}
		listaAnimal = new ArrayList<Animal>();
		listaFotos = new ArrayList<Foto>();

		racaDao = new RacaDao();
		racas = new ArrayList<Raca>();
		selectRacas = new ArrayList<String>();
		listarAnimais();
		popularListaEstado();
		popularListaTipo();
	}

	private void popularListaEstado() {
		listaEstado = Constantes.listaEstados();

	}

	private void popularListaTipo() {
		listaTipo = new ArrayList<String>();
		listaTipo.add("Cachorro");
		listaTipo.add("Gato");
	}

	public void informacaoAnimalOngPessoa() {
		if (animalSelecionado.getOng() != null) {
			nomeUsuario = "ONG: " + animalSelecionado.getOng().getNome();
		} else if (animalSelecionado.getPessoa() != null) {
			nomeUsuario = "Pessoa física: " + animalSelecionado.getPessoa().getNome();
		}

	}

	public void configRacas() {
		racas = racaDao.buscarRacaEspecifica(this.animal.getTipoAnimal());
		selectRacas.clear();
		for (Raca raca : racas) {
			selectRacas.add(raca.getNome());
		}
	}

	public void listarAnimais() {
		listaAnimal = animalDao.buscarAnimalPorStatus("adocao");

		List<Foto> lista = new ArrayList<Foto>();
		for (int i = 0; i < listaAnimal.size(); i++) {

			lista = fDao.buscarListaFotos(listaAnimal.get(i).getId());
			listaAnimal.get(i).setListFotos(lista);
		}

	}

	public void adotarAnimal() {
		animalSelecionado.setStatus("Processo de adocao");
		animalDao.salvar(animalSelecionado);
		listarAnimais();
	}

	public void pesquisarAnimais() {

		if (!animal.getTipoAnimal().isEmpty() && !animal.getRaca().getNome().isEmpty()
				&& !animal.getEstado().isEmpty()) {
			listaAnimal = animalDao.buscarAnimaisTodos(animal.getTipoAnimal(), animal.getRaca().getNome(),
					animal.getEstado());

		} else if (!animal.getTipoAnimal().isEmpty() && animal.getRaca().getNome().isEmpty()
				&& animal.getEstado().isEmpty()) {

			listaAnimal = animalDao.buscarAnimaisSomenteTipo(animal.getTipoAnimal());

		} else if (animal.getTipoAnimal().isEmpty() && !animal.getEstado().isEmpty()) {
			listaAnimal = animalDao.buscarAnimaisSomenteEstado(animal.getEstado());

		} else if (!animal.getTipoAnimal().isEmpty() && !animal.getRaca().getNome().isEmpty()
				&& animal.getEstado().isEmpty()) {
			listaAnimal = animalDao.buscarAnimaisTipoRaca(animal.getTipoAnimal(), animal.getRaca().getNome());

		} else if (!animal.getTipoAnimal().isEmpty() && animal.getRaca().getNome().isEmpty()
				&& !animal.getEstado().isEmpty()) {

			listaAnimal = animalDao.buscarAnimaisTipoEstado(animal.getTipoAnimal(), animal.getEstado());

		} else {
			listarAnimais();
			return;
		}

	}

	public List<Animal> getListaAnimal() {
		return listaAnimal;
	}

	public void setListaAnimal(List<Animal> listaAnimal) {
		this.listaAnimal = listaAnimal;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Foto> getListaFotos() {
		return listaFotos;
	}

	public void setListaFotos(List<Foto> listaFotos) {
		this.listaFotos = listaFotos;
	}

	public Animal getAnimalSelecionado() {
		return animalSelecionado;
	}

	public void setAnimalSelecionado(Animal animalSelecionado) {
		this.animalSelecionado = animalSelecionado;
	}

	public List<Raca> getRacas() {
		return racas;
	}

	public void setRacas(List<Raca> racas) {
		this.racas = racas;
	}

	public List<String> getListaTipo() {
		return listaTipo;
	}

	public void setListaTipo(List<String> listaTipo) {
		this.listaTipo = listaTipo;
	}

	public List<String> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<String> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public List<String> getSelectRacas() {
		return selectRacas;
	}

	public void setSelectRacas(List<String> selectRacas) {
		this.selectRacas = selectRacas;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
