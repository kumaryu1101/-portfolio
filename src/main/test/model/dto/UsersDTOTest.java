package model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsersDTOTest {
    private UsersDTO user;

    @BeforeEach
    void setUp() {
        user = new UsersDTO();
    }

    /**
     * デフォルトコンストラクタの初期状態をテストする。
     * 全フィールドが初期値（nullまたは0）であることを確認する。
     */
    @Test
    void testDefaultConstructor() {
        assertEquals(0, user.getId());
        assertNull(user.getUserName());
        assertNull(user.getDancerName());
    }

    /**
     * ユーザー名とダンサー名のみを指定するコンストラクタの動作をテストする。
     * フィールドが正しく初期化されることを確認する。
     */
    @Test
    void testConstructorWithUsernameAndDancerName() {
        String username = "TestUser";
        String dancername = "TestDancer";

        UsersDTO user = new UsersDTO(username, dancername);

        assertEquals(username, user.getUserName());
        assertEquals(dancername, user.getDancerName());
    }

    /**
     * ID・ユーザー名・ダンサー名を指定するコンストラクタの動作をテストする。
     * すべてのフィールドが正しく初期化されることを確認する。
     */
    @Test
    void testConstructorWithAllFields() {
        int id = 1;
        String username = "User";
        String dancername = "Dancer";

        UsersDTO user = new UsersDTO(id, username, dancername);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUserName());
        assertEquals(dancername, user.getDancerName());
    }

    /**
     * セッターとゲッターの動作をテストする。
     * セッターで設定した値がゲッターで正しく取得できることを確認する。
     */
    @Test
    void testSettersAndGetters() {
        int id = 100;
        String username = "UserName";
        String dancername = "DancerName";

        user.setId(id);
        user.setUserName(username);
        user.setDancerName(dancername);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUserName());
        assertEquals(dancername, user.getDancerName());
    }
}
