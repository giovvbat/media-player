package media_player.modelo;

import java.util.ArrayList;

import media_player.dao.PlaylistDao;

public class UsuarioVIP extends Usuario {
    //ArrayList<Playlist> playlists;
    private PlaylistDao p;

    public PlaylistDao getP() {
        return p;
    }

    public void setP(PlaylistDao p) {
        this.p = p;
    }
    
}
