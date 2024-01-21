package datadives.io.msreviews.common;

import datadives.io.msreviews.model.Analise;

import java.util.Arrays;
import java.util.List;

import static datadives.io.msreviews.common.UsuarioConstants.*;
public class AnaliseConstants {
    public static Analise ANALISE1 = new Analise(1, USER1, "GAME_1_ID", "TEXTO_ANALISE_1", (short) 5);
    public static Analise ANALISE2 = new Analise(2, USER2, "GAME_2_ID", "TEXTO_ANALISE_2", (short) 3);
    public static List<Analise> LISTA_ANALISE = Arrays.asList(ANALISE1, ANALISE2);
}
