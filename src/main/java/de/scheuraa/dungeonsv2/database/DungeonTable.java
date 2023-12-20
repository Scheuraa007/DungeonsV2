package de.scheuraa.dungeonsv2.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import de.scheuraa.dungeonsv2.utils.Dungeon;
import de.scheuraa.dungeonsv2.utils.Rarity;
import de.scheuraa.dungeonsv2.utils.Var;

import java.sql.*;

public class DungeonTable {

    MysqlDataSource dataSource;

    public DungeonTable() {
        this.dataSource = MySQLConnector.getDataSource();
    }


    public int createDungeon(String name, int rarity, String schem){
        dataSource = MySQLConnector.getDataSource();

        if (!Var.getDungeonHandler().existsDungeon(name)) {
            try (Connection conn = dataSource.getConnection()) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO  dungeon (dungeon_name, dungeon_rarity_id, dungeon_schematic_name) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, rarity);
                preparedStatement.setString(3, schem);
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

    public void loadAllDungeons(){
        dataSource = MySQLConnector.getDataSource();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from dungeon");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("dungeon_id");
                String name = resultSet.getString("dungeon_name");
                int rarityId = resultSet.getInt("dungeon_rarity_id");
                String schem = resultSet.getString("dungeon_schematic_name");
                Rarity rarity = Var.getRarityHandler().getRarityById(rarityId);
                if(rarity != null){
                   Dungeon dungeon = new Dungeon(name,rarity,schem,id);
                   Var.getDungeonHandler().getDungeons().add(dungeon);
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateDungeonName(String oldName, String newName){
        dataSource = MySQLConnector.getDataSource();
        if(!Var.getDungeonHandler().existsDungeon(oldName)){
            return false;
        }
        if(Var.getDungeonHandler().existsDungeon(newName)){
            return false;
        }
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE dungeon set dungeon_name ='"+newName +"' where dungeon_name='" +oldName +"'" );
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateDungeonRarity(String name, int id){
        dataSource = MySQLConnector.getDataSource();
        if(!Var.getDungeonHandler().existsDungeon(name)){
            return false;
        }
        if(!Var.getRarityHandler().existsRarity(id)){
            return false;
        }
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE dungeon set dungeon_rarity_id ='"+id +"' where dungeon_name='" +name +"'" );
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateDungeonSchem(String name, String schem){
        dataSource = MySQLConnector.getDataSource();
        if(!Var.getDungeonHandler().existsDungeon(name)){
            return false;
        }

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE dungeon set dungeon_schematic_name ='"+schem +"' where dungeon_name='" +name +"'" );
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteDungeon(String dungeonName) {
        dataSource = MySQLConnector.getDataSource();
        if (Var.getDungeonHandler().existsDungeon(dungeonName)) {
            try (Connection conn = dataSource.getConnection()) {
                PreparedStatement preparedStatement = conn.prepareStatement("DELETE from dungeon where dungeon_name='" + dungeonName + "'");
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }


}
