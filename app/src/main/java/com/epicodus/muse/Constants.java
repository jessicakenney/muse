package com.epicodus.muse;

/**
 * Created by momma on 10/19/17.
 */

//ref
//    https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.collection&access_token=c37cbfe0d837df6dc706875195730201&color=daa520&page=1&per_page=10'

//    https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.collection&access_token=a80be2bad8aec32943183f6134219e6c&query=chair&page=1&per_page=10'

// SEARCHQUERY   https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.objects&access_token=6f159a2bc2c42de659123d2fc7957f9a&query=dog&has_images=1&page=1&per_page=10'
//    getRandom: https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.objects.getRandom&access_token=3d716e36f33a42d828cc679ee585b3db&has_image=1


public class Constants {
    public static final String COOPERHEWITT_BASE_URL = "https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.collection&page=1&per_page=10";
    public static final String COOPERHEWITT_OBJECT_BASE_URL = "https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.objects.getRandom&has_image=1";
    public static final String COOPERHEWITT_SEARCH_BASE_URL = "https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.objects&has_images=1&page=1&per_page=10";
    public static final String API_KEY_QUERY_PARAMETER = "access_token";
    public static final String COOPERHEWITT_TOKEN = BuildConfig.COOPERHEWITT_TOKEN;
    public static final String COOPERHEWITT_COLOR_QUERY_PARAMETER = "color";
    public static final String COOPERHEWITT_QUERY_PARAMETER = "query";

    public static final String FIREBASE_CHILD_ARTIFACTS = "artifacts";
    public static final String PREFERENCES_WORD_KEY = "word";

    public static final String[] CSS3_PALETTE_HEX = new String[] {
            "#7cfc00",
            "#808080",
            "#00008b",
            "#ffe4b5",
            "#fffff0",
            "#9400d3",
            "#b8860b",
            "#fffaf0",
            "#8b008b",
            "#fff5ee",
            "#ff8c00",
            "#5f9ea0",
            "#2f4f4f",
            "#afeeee",
            "#add8e6",
            "#fffafa",
            "#1e90ff",
            "#000000",
            "#8fbc8f",
            "#7fffd4",
            "#ffe4e1",
            "#800000",
            "#6495ed",
            "#4169e1",
            "#40e0d0",
            "#ffffff",
            "#00ced1",
            "#8b0000",
            "#ee82ee",
            "#adff2f",
            "#b0e0e6",
            "#7b68ee",
            "#ff00ff",
            "#4682b4",
            "#ff6347",
            "#00ff7f",
            "#ffefd5",
            "#ffd700",
            "#f5fffa",
            "#dda0dd",
            "#cd5c5c",
            "#ffa07a",
            "#00fa9a",
            "#f8f8ff",
            "#008000",
            "#faebd7",
            "#f0fff0",
            "#c0c0c0",
            "#228b22",
            "#7fff00",
            "#fafad2",
            "#fff8dc",
            "#f4a460",
            "#6b8e23",
            "#9932cc",
            "#fdf5e6",
            "#3cb371",
            "#d8bfd8",
            "#808000",
            "#a9a9a9",
            "#f5f5dc",
            "#f5deb3",
            "#ffa500",
            "#00ff00",
            "#90ee90",
            "#a0522d",
            "#8b4513",
            "#00ffff",
            "#4b0082",
            "#66cdaa",
            "#b22222",
            "#d2b48c",
            "#ffebcd",
            "#0000ff",
            "#da70d6",
            "#fffacd",
            "#ff7f50",
            "#f5f5f5",
            "#ffc0cb",
            "#f0e68c",
            "#ffffe0",
            "#f0ffff",
            "#f0f8ff",
            "#daa520",
            "#c71585",
            "#b0c4de",
            "#fa8072",
            "#708090",
            "#ff1493",
            "#6a5acd",
            "#2e8b57",
            "#e9967a",
            "#778899",
            "#ba55d3",
            "#ff4500",
            "#a52a2a",
            "#696969",
            "#191970",
            "#dc143c",
            "#bdb76b",
            "#ffff00",
            "#d87093",
            "#556b2f",
            "#9370d8",
            "#eee8aa",
            "#cd853f",
            "#faf0e6",
            "#ffdab9",
            "#d2691e",
            "#008080",
            "#98fb98",
            "#ff69b4",
            "#e0ffff",
            "#87cefa",
            "#ffdead",
            "#483d8b",
            "#32cd32",
            "#e6e6fa",
            "#800080",
            "#d3d3d3",
            "#006400",
            "#0000cd",
            "#00bfff",
            "#ffe4c4",
            "#48d1cc",
            "#008b8b",
            "#9acd32",
            "#f08080",
            "#ff0000",
            "#bc8f8f",
            "#ffb6c1",
            "#20b2aa",
            "#87ceeb",
            "#fff0f5",
            "#8a2be2",
            "#000080",
            "#dcdcdc",
            "#deb887" };



}

