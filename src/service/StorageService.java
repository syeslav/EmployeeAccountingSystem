package service;

import model.Organization;
import java.io.*;

public class StorageService {
    public static void saveOrganization(Organization organization, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(organization);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Organization loadOrganization(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Organization) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}