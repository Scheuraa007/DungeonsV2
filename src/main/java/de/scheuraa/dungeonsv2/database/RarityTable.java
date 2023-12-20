package de.scheuraa.dungeonsv2.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.Var;

import java.sql.*;

public class RarityTable {

    MysqlDataSource dataSource;

    public RarityTable() {
        dataSource = MySQLConnector.getDataSource();
    }


    public int createRarity(String rarityName, String rarityPrefix, double rarityPercentage) {
        dataSource = MySQLConnector.getDataSource();
        if (!Var.getRarityHandler().existsRarity(rarityName)) {
            try (Connection conn = dataSource.getConnection()) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO  rarity (rarity_name, rarity_prefix, rarity_percentage) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, rarityName);
                preparedStatement.setString(2, rarityPrefix);
                preparedStatement.setDouble(3, rarityPercentage);
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int auto_id = resultSet.getInt(1);
                resultSet.close();
                return auto_id;
            } catch (SQLException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public boolean deleteRarity(String rarityName) {
        dataSource = MySQLConnector.getDataSource();
        if (Var.getRarityHandler().existsRarity(rarityName)) {
            try (Connection conn = dataSource.getConnection()) {
                PreparedStatement preparedStatement = conn.prepareStatement("DELETE from rarity where rarity_name='" + rarityName + "'");
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public void loadAllRarities() {
        dataSource = MySQLConnector.getDataSource();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from rarity");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("rarity_id");
                String name = resultSet.getString("rarity_name");
                String prefix = resultSet.getString("rarity_prefix");
                double percentage = resultSet.getDouble("rarity_percentage");
                Rarity rarity = new Rarity(name, prefix, percentage,id);
                Var.getRarityHandler().getRarities().add(rarity);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean editRarityName(String oldName, String newName)
    {
        dataSource = MySQLConnector.getDataSource();
        if(!Var.getRarityHandler().existsRarity(oldName)){
            return false;
        }
        if(Var.getRarityHandler().existsRarity(newName)){
            return false;
        }
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE rarity set rarity_name ='"+newName +"' where rarity_name='" +oldName +"'" );
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean editRarityPercentage(String name, double percentage)
    {
        dataSource = MySQLConnector.getDataSource();
        if(!Var.getRarityHandler().existsRarity(name)){
            return false;
        }
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE rarity set rarity_percentage ='"+percentage +"' where rarity_name='" +name +"'" );
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean editRarityPrefix(String name, String prefix)
    {
        dataSource = MySQLConnector.getDataSource();
        if(!Var.getRarityHandler().existsRarity(name)){
            return false;
        }
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE rarity set rarity_prefix ='"+prefix +"' where rarity_name='" +name +"'" );
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public int getIDbyName(String name) {
        dataSource = MySQLConnector.getDataSource();
        if(!Var.getRarityHandler().existsRarity(name)){
            return 0;
        }
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT rarity_id from rarity where rarity_name='" +name +"'" );
            ResultSet resultSet = preparedStatement.executeQuery();
            int id =0;
            if(resultSet.next()){
                id = resultSet.getInt("rarity_id");
            }
            resultSet.close();
            return id;
        } catch (SQLException e) {
            return 0;
        }
    }
}
