/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.TriviaQuestionDao;
import Dao.TriviaParticipantDao;
import Model.TriviaPartivipantModel;
import Model.TriviaQuestioModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriviaController {
    private TriviaQuestionDao triviaQuestionDao;
    private TriviaParticipantDao triviaParticipantDao;
    private List<TriviaPartivipantModel> participantes;
    private int rondaActual;

    public TriviaController() {
        this.triviaQuestionDao = new TriviaQuestionDao();
        this.triviaParticipantDao = new TriviaParticipantDao();
        this.rondaActual = 1;
    }

    // Método para iniciar el concurso
    public void iniciarConcurso(int triviaContestID) throws SQLException {
        participantes = obtenerParticipantes(triviaContestID);
        while (participantes.size() > 1) {
            realizarRonda();
            rondaActual++;
        }
        System.out.println("El ganador del concurso es el participante con ID: " + participantes.get(0).getParticipationID());
    }

    // Método para realizar una ronda
    private void realizarRonda() throws SQLException {
        System.out.println("Iniciando ronda " + rondaActual);
        List<TriviaPartivipantModel> ganadores = new ArrayList<>();

        Collections.shuffle(participantes);
        for (int i = 0; i < participantes.size() - 1; i += 2) {
            TriviaPartivipantModel p1 = participantes.get(i);
            TriviaPartivipantModel p2 = participantes.get(i + 1);
            TriviaPartivipantModel ganador = enfrentar(p1, p2);
            ganadores.add(ganador);
        }

        participantes = ganadores;
    }

    // Método para enfrentar dos participantes
    private TriviaPartivipantModel enfrentar(TriviaPartivipantModel p1, TriviaPartivipantModel p2) throws SQLException {
        System.out.println("Enfrentamiento entre los participantes con ID: " + p1.getParticipationID() + " y " + p2.getParticipationID());
        List<TriviaQuestioModel> preguntas = triviaQuestionDao.getQuestionsByDifficulty(obtenerDificultadPorRonda(rondaActual));
        int puntosP1 = 0;
        int puntosP2 = 0;

        for (TriviaQuestioModel pregunta : preguntas) {
            System.out.println("Pregunta: " + pregunta.getQuestion());
            puntosP1++; // Simulación de respuestas (esto se puede cambiar por lógica real)
            if (puntosP1 == 3 || puntosP2 == 3) {
                break;
            }
        }

        return (puntosP1 > puntosP2) ? p1 : p2;
    }

    // Método para registrar una nueva pregunta
    public void registrarPregunta(TriviaQuestioModel pregunta) throws SQLException {
        triviaQuestionDao.addTriviaQuestion(pregunta);
        System.out.println("Pregunta registrada exitosamente.");
    }

    // Método para registrar un nuevo participante
    public void registrarParticipante(TriviaPartivipantModel participante) throws SQLException {
        triviaParticipantDao.addTriviaParticipant(participante);
        System.out.println("Participante registrado exitosamente.");
    }

    // Método para visualizar resultados (simplificado)
    public void mostrarResultados() {
        // Lógica para mostrar resultados de los participantes
        System.out.println("Resultados mostrados.");
    }

    // Métodos auxiliares
    private List<TriviaPartivipantModel> obtenerParticipantes(int triviaContestID) {
        // Simulación de obtención de participantes de la base de datos
        return new ArrayList<>(); // Retorna la lista de participantes
    }

    private String obtenerDificultadPorRonda(int ronda) {
        switch (ronda) {
            case 1: return "Facil";
            case 2: return "Intermedio";
            case 3: return "Dificil";
            default: return "Dificil";
        }
    }
}

