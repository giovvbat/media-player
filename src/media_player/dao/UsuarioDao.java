package media_player.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import media_player.modelo.Usuario;
import media_player.modelo.UsuarioVIP;

public class UsuarioDao {
    private ArrayList<Usuario> usuarios;

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void carregarUsuarios() throws IOException {
        String caminhoArquivo = "arquivos/usuarios.txt";
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()){
            arquivo.getParentFile().mkdirs();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String login;

            while((login = br.readLine()) != null) {
                String senha = br.readLine();
                String tipo = br.readLine();
                Usuario usuario;

                if(tipo == "VIP") {
                    usuario = new UsuarioVIP();
                }
                else {
                    usuario = new Usuario();
                }
                
                usuario.setLogin(login); 
                usuario.setSenha(senha);
                usuarios.add(usuario);
            }
        }
        catch(IOException e) {
            throw e;
        }
    }

    public void cadastrarUsuario(String nome, String senha, String tipo) {
        Usuario usuario;
        if(tipo == "VIP") {
            usuario = new UsuarioVIP(); 
        } 
        else {
            usuario = new Usuario();
        }
    
        usuario.setLogin(nome);
        usuario.setSenha(senha);

        usuarios.add(usuario);
    }
    

}