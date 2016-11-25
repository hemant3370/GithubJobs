package githubjobs.georgianhemant.com.githubjobs.Dagger.Component;




import javax.inject.Singleton;

import dagger.Component;
import githubjobs.georgianhemant.com.githubjobs.Dagger.Module.AppModule;
import githubjobs.georgianhemant.com.githubjobs.Dagger.Module.NetModule;
import githubjobs.georgianhemant.com.githubjobs.Activities.MainActivity;

/**
 * Created by Rahul on 6/20/2016.
 */
@Singleton
@Component(modules = {NetModule.class, AppModule.class})
public interface NetComponent {

    void inject(MainActivity activity);























}
