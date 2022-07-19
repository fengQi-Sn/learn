package example.example.structure.proxy;

public class UserController implements IUserController {
    @Override
    public void login() {
        System.out.println("nao gua zi login");
    }
}
