package sprite;

import java.util.List;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * sprite.SpriteCollection holds a collection of sprites.
 */
public class SpriteCollection {
    // members
    private List<Sprite> sprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * method that adds a new sprite to the list.
     *
     * @param s the new sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * method that removes a given sprite from the list.
     *
     * @param s the given sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite sprite : copy) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the screen.
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite sprite : copy) {
            sprite.drawOn(d);
        }
    }
}