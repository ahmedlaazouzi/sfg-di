package Dependancy.sfgdi.repositories;

public class EnglishGreetingRepositoryImpl implements EnglishGreetingRepository{
    @Override
    public String getGreeting() {
        return "Say Hello Repository" ;
    }
}
