package ifpr.pgua.eic.tarefas.model.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.LavaCarDAO;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;

public class RepositorioLavaCar {
    private LavaCarDAO dao;

    public RepositorioLavaCar(LavaCarDAO dao){
        this.dao = dao;
    }

    public Resultado cadastrarLavacar(String nome, String login, String senha){
        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }
        if(login.isEmpty() || login.isBlank()){
            return Resultado.erro("Login inválido!");
        }
        if(senha.isEmpty() || senha.isBlank()){
            return Resultado.erro("Senha inválido!");
        }


        LavaCar lavacar = new LavaCar(nome, login, senha);

        return dao.criar(lavacar);
    }
}
