package datadives.io.msreviews.common;

import datadives.io.msreviews.model.Usuario;

import java.util.Arrays;
import java.util.List;

public class UsuarioConstants {
    public static Usuario USER1 = new Usuario(1, "gerson", "strongPassword", "");
    public static Usuario USER2 = new Usuario(2, "maria", "newPassword", "somePhone");
    public static List<Usuario> USER_LIST = Arrays.asList(USER1, USER2);
}
