package org.wcci.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

@Component
public class Populator implements CommandLineRunner {

    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private PostStorage postStorage;
    private TagStorage tagStorage;

    public Populator(CategoryStorage categoryStorage, AuthorStorage authorStorage, PostStorage postStorage, TagStorage tagStorage) {
        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.postStorage = postStorage;
        this.tagStorage = tagStorage;
    }

    @Override
    public void run(String... args) {
        Author colin = new Author("Colin W.");
        Author john = new Author("John J.");
        Author eric = new Author("Eric N.");
        authorStorage.store(colin);
        authorStorage.store(john);
        authorStorage.store(eric);

        Category programming = new Category("Programming");
        Category cool = new Category("Cool");
        Category lifeHacks = new Category("Life Hacks");
        categoryStorage.store(programming);
        categoryStorage.store(lifeHacks);
        categoryStorage.store(cool);

        Post post1 = new Post("How to Use ColdFusion", "Dont.", colin, programming);
        Post post2 = new Post("Wicked Kicks", "Recent launches from Danish shoe company ECCO would suggest that the sky really is the limit when it comes to golf shoe design. The Cage model was daring in its looks but the innovations that featured always had the performance benefits to the golfer in mind.\n" +
                "\n" +
                "This theme continues into the ECCO Cool shoe. Its eye-catching design is the product of tireless work with waterproof specialists GORE, with unique Gore-Tex Surround technology at the heart of the shoe’s performance.", john, cool);
        Post post3 = new Post("Making a Pencil", "Find a twig. Look for one with interesting color, texture or with a forked shape. (The length of the twig, though, must be straight.) Hold the twig as you would a pencil to find the right size. Use pruning clippers to trim away unwanted parts. Check the twig for bugs (you don’t want any). \n" +
                "\n" +
                "Clamp the twig to the edge of a workbench or piece of plywood. Be careful! Too much pressure can crush the twig.\n" +
                "\n" +
                " Use a scratch awl or the point of a nail to make a dent at the center of the twig’s end. The dent will become the starting point for the drill bit.\n" +
                "\n" +
                " Drill to a depth of 1 to 1-1/4 inches. Make sure to keep both hands on the tool!\n" +
                "\n" +
                "\n" +
                "\n" +
                "As you drill, you may need to back out the bit to clear wood chips from the drill’s flutes (its spiral grooves). To do this, stop the drill and scrub the bit with an old toothbrush. Squirt a small puddle of glue on a scrap of wood or cardboard. Roll the end of the lead in the glue, then work it back and forth in the hole to spread the adhesive.\n" +
                "\n" +
                " Trim the lead by breaking it sideways against the twig. Let the glue dry overnight. \n" +
                "\n" +
                "Sharpen the pencil with your pocketknife or a sharp utility knife. Whittle away from your body, removing thin shavings as you work around the pencil. Use your imagination to personalize your pencil, or simply enjoy the colors and textures that nature provides.", eric, lifeHacks);
        postStorage.store(post1);
        postStorage.store(post2);
        postStorage.store(post3);

        Tag tag1 = new Tag("#cool", post1, post2);
        Tag tag2 = new Tag("#programming", post1);
        Tag tag3 = new Tag("#lifehack", post3);
        tagStorage.store(tag1);
        tagStorage.store(tag2);
        tagStorage.store(tag3);

    }
}
