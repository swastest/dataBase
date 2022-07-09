package actions;

import config.AuthUsers;
import connectBD.DataSourceProvider;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetFromDataBase {
    private DataSource ds = DataSourceProvider.INSTANCE.getDataSource();
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ds);
    AuthUsers config = ConfigFactory.create(AuthUsers.class);

    public String findConfirmSmsCodeExecutor() {
        //   String sqlConfirmCode = "SELECT phone_confirm_code FROM app_user WHERE id = :userId";
        String sqlConfirmCode = "SELECT phone_confirm_code FROM app_user WHERE role = :roleName and phone like CONCAT('%',:phoneNumber)";
        Map<String, Object> map = new HashMap<>();
        //  map.put("userId", 526);
        map.put("roleName", "ROLE_EXECUTOR");
        map.put("phoneNumber", config.loginExecutor());
        //rs - resultSet   i - строка номер
        List<String> confirmCodeSms = namedParameterJdbcTemplate.query(sqlConfirmCode, map, (rs, i) -> rs.getString("phone_confirm_code"));
        return confirmCodeSms.get(0);
    }


    public String findConfirmCallCodeExecutor() {
        //      String sqlConfirmCode = "SELECT phone_call_confirm_code FROM app_user WHERE id = :userId";
        String sqlConfirmCode = "SELECT phone_call_confirm_code FROM app_user WHERE role = :roleName and phone like CONCAT('%',:phoneNumber)";
        Map<String, Object> map = new HashMap<>();
        //     map.put("userId", 526);
        map.put("roleName", "ROLE_EXECUTOR");
        map.put("phoneNumber", config.loginExecutor());
        List<String> confirmCodeSms = namedParameterJdbcTemplate.query(sqlConfirmCode, map, (rs, i) -> rs.getString("phone_call_confirm_code"));
        return confirmCodeSms.get(0);
    }
    public void deleteUser(Integer value) {
        String delUser = "DELETE FROM app_user WHERE id = :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", value);
        namedParameterJdbcTemplate.update(delUser,map);

    }

    @Test
    void test01(){
        deleteUser(2);
    }

}
