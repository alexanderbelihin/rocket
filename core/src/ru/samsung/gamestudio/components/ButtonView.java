package ru.samsung.gamestudio.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonView extends View {

    private Texture texture;
    private BitmapFont bitmapFont;
    private String text;
    private GlyphLayout layout = new GlyphLayout(); // Используем один объект для расчетов

    // Конструктор с текстом
    public ButtonView(float x, float y, float width, float height, BitmapFont font, String texturePath, String text) {
        super(x, y, width, height);
        this.text = text;
        this.bitmapFont = font;
        this.texture = new Texture(texturePath);
    }

    // Конструктор без текста
    public ButtonView(float x, float y, float width, float height, String texturePath) {
        super(x, y, width, height);
        this.texture = new Texture(texturePath);
    }

    @Override
    public void draw(SpriteBatch batch) {
        // Рисуем текстуру кнопки
        batch.draw(texture, x, y, width, height);

        // Если есть шрифт и текст — рисуем его строго по центру
        if (bitmapFont != null && text != null) {
            layout.setText(bitmapFont, text);
            float textX = x + (width - layout.width) / 2;
            float textY = y + (height + layout.height) / 2;
            bitmapFont.draw(batch, text, textX, textY);
        }
    }

    @Override
    public void dispose() {
        // Удаляем только текстуру, так как она уникальна для кнопки
        if (texture != null) texture.dispose();
        // bitmapFont.dispose();
    }
}