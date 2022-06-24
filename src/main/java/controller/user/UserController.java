package controller.user;

import javafx.scene.image.Image;
import model.user.User;

/**
 * 
 * generic interface of userControllerImpl.
 *
 */
public interface UserController extends User {

    /**
     * Getter for Image.
     * @return user image
     */
    Image getImage();

    /**
     * Getter for user.
     * @return user 
     */
    User getUser();
}
