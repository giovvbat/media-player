package media_player.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

import media_player.modelo.Usuario;
import media_player.modelo.UsuarioVIP;

public class UsuarioDao {
    private ArrayList<Usuario> usuarios;    
    private int ultimoId = 0;

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
                int id = Integer.parseInt(br.readLine());

                if(id > ultimoId) {
                    ultimoId = id;
                }

                Usuario usuario;

                if(tipo == "VIP") {
                    usuario = new UsuarioVIP();
                }
                else {
                    usuario = new Usuario();
                }
                
                usuario.setLogin(login); 
                usuario.setSenha(senha);
                usuario.setId(ultimoId);
                usuarios.add(usuario);
            }
        }
        catch(IOException e) {
            throw e;
        }
    }

    public void cadastrarUsuario(String nome, String senha, String tipo) throws IOException {
        Usuario usuario;
        if(tipo == "VIP") {
            usuario = new UsuarioVIP(); 
        } 
        else {
            usuario = new Usuario();
        }
    
        usuario.setLogin(nome);
        usuario.setSenha(senha);
        ultimoId++;
        usuario.setId(ultimoId);

        String caminhoUsuarios = "arquivos/usuarios.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoUsuarios))) {
            writer.write(usuario.getLogin());
            writer.newLine();
            writer.write(usuario.getSenha());
            writer.newLine();
            writer.write(usuario.getId());
            writer.newLine();
        } 
        catch (IOException e) {
            throw e;
        }
            
        usuarios.add(usuario);
    }
    

}