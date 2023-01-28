package com.edu.ProyectoMorty.EpisodeController;


import com.edu.ProyectoMorty.apiConsumer.ConsumirApi;
import com.edu.ProyectoMorty.apiConsumer.ConsumirApiCharacter;
import com.edu.ProyectoMorty.paginacion.Paginacion;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class ControladorEpisode {
    @Autowired
    ConsumirApi consumirApi  ;
    @Autowired
    ConsumirApiCharacter consumirPersonajes;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/episodios")
    private String listarEpi(Model model) throws IOException {
        model.addAttribute("episodes", consumirApi.listEpisodes(Paginacion.VACIO));
        return "listaDeEpisodios";
    }


    @GetMapping("/paginar/{id}")
    public String pagina(Model model, @PathVariable("id") int id) throws IOException {
        if (id==1) {
            model.addAttribute("episodes", consumirApi.listEpisodes(Paginacion.PREVIOUS));
        }else if (id ==2){
            
            model.addAttribute("episodes",consumirApi.listEpisodes(Paginacion.NEXT));
        }
        return "listaDeEpisodios";
    }

    @GetMapping("/personaje/{url}")
    public String getPersonaje(Model model, @PathVariable("url") String url) throws IOException {
        model.addAttribute("personaje", consumirApi.getCharacter(url));
        return "Personaje";
    }

    //Listar todos los personajes
    @GetMapping("/personajes")
    private String listarPerso(Model model) throws IOException {
        model.addAttribute("personajes", consumirPersonajes.listPersonajes(Paginacion.VACIO));
        return "listaPersonajes";
    }

    @GetMapping("/pagina-personaje/{id}")
    public String paginaPersonajes(Model model, @PathVariable("id") int id) throws IOException {
        if (id==1) {
            model.addAttribute("personajes", consumirPersonajes.listPersonajes(Paginacion.PREVIOUS));
        }else if (id ==2){

            model.addAttribute("personajes",consumirPersonajes.listPersonajes(Paginacion.NEXT));
        }
        return "listaPersonajes";
    }
}
