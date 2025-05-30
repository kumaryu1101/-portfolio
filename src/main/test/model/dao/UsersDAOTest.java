package model.dao;

import model.dto.UsersDTO;

public class UsersDAOTest {

    public static void main(String[] args) throws Exception {
        UsersDAO dao = new UsersDAO();

        // ① findByid() メソッドのテスト
        testFindById(dao);

        // ② selectUser() メソッドのテスト
        testSelectUser(dao);

        // ③ intsertUser() メソッドのテスト
        testInsertUser();

        // ④ updateUser() メソッドのテスト
        testUpdateUser(dao);

        // ⑤ deleteUser() メソッドのテスト
        //他のテーブルから参照されているため、削除できず
        testDeleteUser(dao);
    }

    /**
     * ① findByid メソッドでユーザー取得のテスト
     */
    private static void testFindById(UsersDAO dao) throws ClassNotFoundException {
        System.out.println("① findByid メソッドのテスト開始");
        UsersDTO user = dao.findByid(1);  // id=1は環境に合わせて存在するIDを指定
        if (user.getId() != 0) {
            System.out.println("ユーザー取得成功: ID=" + user.getId() + ", username=" + user.getUserName() + ", dancername=" + user.getDancerName());
        } else {
            System.out.println("ユーザーが見つかりませんでした");
        }
        System.out.println("① テスト終了\n");
    }

    /**
     * ② selectUser メソッドのテスト
     */
    private static void testSelectUser(UsersDAO dao) throws Exception {
        System.out.println("② selectUser メソッドのテスト開始");
        UsersDTO user = new UsersDTO();
        user.setId(1); // 存在するIDをセット
        dao.selectUser(user);
        System.out.println("② テスト終了\n");
    }

    /**
     * ③ intsertUser メソッドのテスト
     */
    public static void testInsertUser() throws Exception {
        System.out.println("③ intsertUser メソッドのテスト開始");
        UsersDAO dao = new UsersDAO();

        // テスト用のユーザー名・ダンサー名
        String testUsername = "testUser_" + System.currentTimeMillis();
        String testDancername = "testDancer_" + System.currentTimeMillis();

        // インサート実行
        dao.intsertUser(testUsername, testDancername);

        // insert後、idが自動なので、usernameで検索して取得してみる
        UsersDTO insertedUser = dao.findByUsername(testUsername);

        if (insertedUser != null && insertedUser.getUserName().equals(testUsername)) {
            System.out.println("インサート成功: ID=" + insertedUser.getId() + ", username=" + insertedUser.getUserName() + ", dancername=" + insertedUser.getDancerName());
        } else {
            System.out.println("インサート失敗");
        }
        System.out.println("③ テスト終了");
    }


    /**
     * ④ updateUser メソッドのテスト
     */
    private static void testUpdateUser(UsersDAO dao) throws Exception {
        System.out.println("④ updateUser メソッドのテスト開始");
        UsersDTO user = dao.findByid(1);  // 存在するID
        if (user.getId() != 0) {
            String newDancerName = "UpdatedDancer_" + System.currentTimeMillis();
            dao.updateUser(user, newDancerName);
            System.out.println("更新完了: 新しいdancername=" + newDancerName);
        } else {
            System.out.println("更新対象のユーザーが存在しません");
        }
        System.out.println("④ テスト終了\n");
    }

    /**
     * ⑤ deleteUser メソッドのテスト
     * ※ 事前に追加したテスト用ユーザーを削除する想定
     */
    private static void testDeleteUser(UsersDAO dao) throws Exception {
        System.out.println("⑤ deleteUser メソッドのテスト開始");
        // まず、insertUserでテストユーザー作成（IDは自動生成なので別途取得が必要だが今回は割愛）
        // ここでは、id=1を削除対象と仮定
        UsersDTO user = new UsersDTO();
        user.setId(1);  // 存在するIDをセットしてください
        dao.deleteUser(user);
        System.out.println("⑤ テスト終了\n");
    }
}
