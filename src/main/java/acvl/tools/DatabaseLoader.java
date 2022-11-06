package acvl.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import acvl.modele.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;

public class DatabaseLoader {
    public static List<Professionnels_de_sante> prosList;
    public static List<Agent_administratif> agentsList;
    public static List<String> jobList;
    public static List<String> stateList;
    public static List<Centre> centresList;
    private static String path = System.getProperty("user.dir") + "\\src\\main\\resources\\acvl\\database\\database.json";
    public static void load() throws IOException, ParseException {
        jobList = new ArrayList<>();
        jobList.add("Infirmier");
        jobList.add("Secouriste");
        jobList.add("Ambulancier");
        jobList.add("Médecin Régulateur");
        jobList.add("Médecin Fixe");
        jobList.add("Médecin Mobile");
        jobList.add("Opérateur médical");

        JSONParser parser = new JSONParser();
        prosList = new ArrayList<>();
        centresList = new ArrayList<>();
        stateList = new ArrayList<>();
        agentsList = new ArrayList<>();

        for (Etat nom : Etat.values()) {
            stateList.add(nom.toString());
        }

        try {
            FileReader fr = new FileReader(path);
            JSONObject a = (JSONObject) parser.parse(fr);

            JSONArray centresArray = (JSONArray) a.get("centres");

            Iterator<JSONObject> iterator = centresArray.iterator();
            while (iterator.hasNext()) {
                JSONObject centre = iterator.next();

                if (centre.containsKey("activites")) {
                    List<Domaine_Activite> domaineList = new ArrayList<>();
                    JSONArray domaines = (JSONArray) centre.get("activites");

                    Iterator<String> it = domaines.iterator();
                    while (it.hasNext()) {
                        switch (it.next()) {
                            case "Urgences":
                                domaineList.add(Domaine_Activite.Urgences);
                                break;

                            case "Cardiologie":
                                domaineList.add(Domaine_Activite.Cardiologie);
                                break;

                            case "Reeducation":
                                domaineList.add(Domaine_Activite.Reeducation);
                                break;

                            default:
                                break;
                        }
                    }

                    centresList.add(new Centre_de_soin(centre.get("nom").toString(), centre.get("adresse").toString(), ((Long) centre.get("capacite")).intValue(), domaineList));

                } else if (centre.containsKey("capacite")) {
                    centresList.add(new Centre_de_regulation(centre.get("nom").toString(), centre.get("adresse").toString(), ((Long) centre.get("capacite")).intValue()));

                } else {
                    centresList.add(new Centre_de_secours(centre.get("nom").toString(), centre.get("adresse").toString(), ((Long) centre.get("nbvehicules")).intValue()));
                }
            }

            JSONArray agentsArray = (JSONArray) a.get("agents");

            iterator = agentsArray.iterator();
            while (iterator.hasNext()) {
                JSONObject agent = iterator.next();
                agentsList.add(new Agent_administratif(agent.get("nom").toString(), agent.get("prenom").toString(), agent.get("email").toString(), agent.get("login").toString(), agent.get("motdepasse").toString()));
            }

            JSONArray prosArray = (JSONArray) a.get("pros");

            iterator = prosArray.iterator();
            while (iterator.hasNext()) {
                JSONObject pro = iterator.next();
                Etat e;

                switch (((Long)pro.get("etat")).intValue()) {
                    case 0:
                        e = Etat.disponible_base;
                        break;

                    case 1:
                        e = Etat.en_attente_intervention;
                        break;

                    case 2:
                        e = Etat.disponible_hors_base;
                        break;

                    case 3:
                        e = Etat.intervention;
                        break;

                    case 4:
                        e = Etat.indisponible;
                        e.setMotif(pro.get("motif").toString());
                        break;

                    default:
                        e = Etat.disponible_base;
                        break;
                }

                switch (((Long)pro.get("job")).intValue()) {
                    case 0:
                        prosList.add(new Infirmiers(pro.get("nom").toString(),
                                pro.get("prenom").toString(),
                                pro.get("email").toString(),
                                pro.get("adresse").toString(),
                                centresList.get(((Long)pro.get("centre")).intValue()),
                                e,
                                pro.get("login").toString(),
                                pro.get("motdepasse").toString()));
                        break;

                    case 1:
                        prosList.add(new Secouristes(pro.get("nom").toString(),
                                pro.get("prenom").toString(),
                                pro.get("email").toString(),
                                pro.get("adresse").toString(),
                                centresList.get(((Long)pro.get("centre")).intValue()),
                                e,
                                pro.get("login").toString(),
                                pro.get("motdepasse").toString()));
                        break;

                    case 2:
                        prosList.add(new Ambulanciers(pro.get("nom").toString(),
                                pro.get("prenom").toString(),
                                pro.get("email").toString(),
                                pro.get("adresse").toString(),
                                centresList.get(((Long)pro.get("centre")).intValue()),
                                e,
                                pro.get("login").toString(),
                                pro.get("motdepasse").toString()));
                        break;

                    case 3:
                    case 4:
                    case 5:
                        Medecins proSante = new Medecins(pro.get("nom").toString(),
                                pro.get("prenom").toString(),
                                pro.get("email").toString(),
                                pro.get("adresse").toString(),
                                centresList.get(((Long)pro.get("centre")).intValue()),
                                e,
                                pro.get("login").toString(),
                                pro.get("motdepasse").toString(),
                                ((Long)pro.get("job")).intValue() == 3 ? Statut.Régulateur : ((Long)pro.get("job")).intValue() == 4 ? Statut.Fixe : Statut.Mobile);

                        JSONArray patientsArray = (JSONArray) pro.get("patients");

                        Iterator<JSONObject> it = patientsArray.iterator();
                        proSante.patientList = new ArrayList<>();
                        while (it.hasNext()) {
                            JSONObject patient = it.next();
                            proSante.patientList.add(new Patient(
                                    patient.get("nom").toString(),
                                    patient.get("prenom").toString(),
                                    patient.get("avis").toString()
                            ));
                        }

                        prosList.add(proSante);
                        break;

                    case 6:
                        prosList.add(new Operateurs_medicaux(pro.get("nom").toString(),
                                pro.get("prenom").toString(),
                                pro.get("email").toString(),
                                pro.get("adresse").toString(),
                                centresList.get(((Long)pro.get("centre")).intValue()),
                                e,
                                pro.get("login").toString(),
                                pro.get("motdepasse").toString()
                        ));
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        JSONObject json = new JSONObject();

        try {
            JSONArray proJSONArray = new JSONArray();

            for (Professionnels_de_sante p : prosList) {
                JSONObject proJSON = new JSONObject();
                proJSON.put("job", p.getId());
                proJSON.put("nom", p.getNom());
                proJSON.put("prenom", p.getPrenom());
                proJSON.put("email", p.getAdresseMail());
                proJSON.put("login", p.getLogin());
                proJSON.put("motdepasse", p.getMotdepasse());
                proJSON.put("adresse", p.getAdresseResidence());
                proJSON.put("centre", centresList.indexOf(p.getCentreActuel()));
                proJSON.put("etat", stateList.indexOf(p.getEtatActuel().name()));
                if (p.getEtatActuel().equals(Etat.indisponible)) {
                    proJSON.put("motif", p.getEtatActuel().getMotif());
                }

                if (p instanceof Medecins) {
                    Medecins m = (Medecins) p;
                    JSONArray patientsArray = new JSONArray();

                    for (Patient patient : m.patientList) {
                        JSONObject patientJSON = new JSONObject();

                        patientJSON.put("nom", patient.getNom());
                        patientJSON.put("prenom", patient.getPrenom());
                        patientJSON.put("avis", patient.getAvis());

                        patientsArray.add(patientJSON);
                    }

                    proJSON.put("patients", patientsArray);
                }

                proJSONArray.add(proJSON);
            }

            JSONArray agentsJSONArray = new JSONArray();

            for (Agent_administratif a : agentsList) {
                JSONObject agentJSON = new JSONObject();
                agentJSON.put("nom", a.getNom());
                agentJSON.put("prenom", a.getPrenom());
                agentJSON.put("email", a.getAdresseMail());
                agentJSON.put("login", a.getLogin());
                agentJSON.put("motdepasse", a.getPwd());

                agentsJSONArray.add(agentJSON);
            }

            JSONArray centresJSONArray = new JSONArray();

            for (Centre c : centresList) {
                centresJSONArray.add(c.toJSON());
            }

            json.put("pros", proJSONArray);
            json.put("centres", centresJSONArray);
            json.put("agents", agentsJSONArray);


        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
