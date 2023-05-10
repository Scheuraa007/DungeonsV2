package de.scheuraa.dunegonsv2.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import de.scheuraa.dunegonsv2.utils.Rarity;
import de.scheuraa.dunegonsv2.utils.Var;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RarityTable {

    MysqlDataSource dataSource;

    public RarityTable() {
        dataSource = MySQLConnector.getDataSource();
    }

    public boolean existsRarity(String rarityName) {
        dataSource = MySQLConnector.getDataSource();

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * from rarity where rarity.rarity_name ='" + rarityName + "'"); ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next();
            } catch (SQLException e) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean createRarity(String rarityName, String rarityPrefix, double rarityPercentage) {
        dataSource = MySQLConnector.getDataSource();
        if (!existsRarity(rarityName)) {
            try (Connection conn = dataSource.getConnection()) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO  rarity (rarity_name, rarity_prefix, rarity_percentage) VALUES (?,?,?)");
                preparedStatement.setString(1, rarityName);
                preparedStatement.setString(2, rarityPrefix);
                preparedStatement.setDouble(3, rarityPercentage);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteRarity(String rarityName) {
        dataSource = MySQLConnector.getDataSource();
        if (existsRarity(rarityName)) {
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
                String name = resultSet.getString("rarity_name");
                String prefix = resultSet.getString("rarity_prefix");
                double percentage = resultSet.getDouble("rarity_percentage");
                Rarity rarity = new Rarity(name, prefix, percentage);
                Var.getRarities().add(rarity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
