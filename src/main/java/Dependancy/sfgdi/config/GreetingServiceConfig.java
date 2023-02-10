package Dependancy.sfgdi.config;

import Dependancy.pets.CatPetService;
import Dependancy.pets.DogPetService;
import Dependancy.pets.PetService;
import Dependancy.pets.PetServiceFactory;
import Dependancy.sfgdi.dataSource.FakeDataSource;
import Dependancy.sfgdi.repositories.EnglishGreetingRepository;
import Dependancy.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import Dependancy.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
//uncomment this line if you want to use datasource.properties file and copy/paste from applications pertires file
//@PropertySource("classpath:datasource.properties")
@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {
@Bean
    FakeDataSource fakeDataSource(@Value("${br.username}") String username,
                                  @Value("${br.password}")String password,
                                  @Value("${br.jdbcurl}")String urlbd){
    FakeDataSource fakeDataSource =new FakeDataSource();
    fakeDataSource.setUserName(username);
    fakeDataSource.setPassword(password);
    fakeDataSource.setJdbcurl(urlbd);
    return fakeDataSource;
}
    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }
//uncomment this if you want to not use xml configuration
   /* @Bean
    ConstructorGreetingService constructorGreetingService() {
        return new ConstructorGreetingService();
    }
*/

    //uncomment this if you want to not use xml configuration
  /*  @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService() {
        return new PropertyInjectedGreetingService();
    }*/

    @Bean
    SetterGreetingInjectedService setterGreetingInjectedService() {
        return new SetterGreetingInjectedService();
    }
    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("En")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Profile({"Ar", "default"})
    @Bean("i18nService")
    I18nArabeGreetingService i18nArabicService() {
        return new I18nArabeGreetingService();
    }

    @Bean
    PetServiceFactory petServiceFactory(){
        return new PetServiceFactory();
    };
    @Profile("cat")
    @Bean
    PetService catPetService(PetServiceFactory petServiceFactory) {
       return petServiceFactory.getPetService("cat");

    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("dog");
    }
}


