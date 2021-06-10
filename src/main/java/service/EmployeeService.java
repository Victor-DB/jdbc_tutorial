package service;

import bi.Util;
import dao.EmployeeDAO;
import entity.EmplProj;
import entity.Employee;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService  extends Util implements EmployeeDAO {

    private Connection connection = getConnection();


    public void add(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID) " +
                "VALUES(?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setDate(4, employee.getBirthday());
            preparedStatement.setLong(5, employee.getAddressID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public List<Employee> getAll() throws SQLException {
        List<Employee> employeeList = new ArrayList<Employee>();

        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID " +
                "FROM PUBLIC.EMPLOYEE";

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("ID"));
                employee.setFirstName(resultSet.getString("FIRST_NAME"));
                employee.setLastName(resultSet.getString("LAST_NAME"));
                employee.setBirthday(resultSet.getDate("BIRTHDAY"));
                employee.setAddressID(resultSet.getLong("ADDRESS_ID"));

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employeeList;
    }


    public Employee getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS WHERE ID=?";

        Employee employee = new Employee();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            employee.setId(resultSet.getLong("ID"));
            employee.setFirstName(resultSet.getString("FIRST_NAME"));
            employee.setLastName(resultSet.getString("LAST_NAME"));
            employee.setBirthday(resultSet.getDate("BIRTHDAY"));
            employee.setAddressID(resultSet.getLong("ADDRESS_ID"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employee;
    }


    public void update(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE EMPLOYEE SET FIRST_NAME=?, LAST_NAME=?, BIRTHDAY=?, ADDRESS_ID=0 WHERE ID=0";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, employee.getBirthday());
            preparedStatement.setLong(4, employee.getAddressID());
            preparedStatement.setLong(5, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void remove(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM EMPLOYEE WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

