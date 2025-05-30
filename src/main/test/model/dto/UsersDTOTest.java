package model.dto;

public class UsersDTOTest {

    public static void main(String[] args) {
        // インスタンス作成
        UsersDTO user = new UsersDTO();

        // セッターで値をセット
        user.setId(10);
        user.setUserName("testuser");
        user.setDancerName("dancerTest");

        // ゲッターで値を取得し、期待値と比較して検証
        if (user.getId() == 10
                && "testuser".equals(user.getUserName())
                && "dancerTest".equals(user.getDancerName())) {
            System.out.println("UsersDTOのゲッター・セッター テスト成功！");
        } else {
            System.out.println("UsersDTOのゲッター・セッター テスト失敗！");
        }
    }
}
