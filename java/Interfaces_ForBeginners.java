// java/Interfaces_ForBeginners.java

/**
 * Java 接口入门 (Interfaces_ForBeginners)
 *
 * 接口 (Interface) 是Java中一个非常重要的概念。你可以把它看作是一份“合同”或“规范”，
 * 它定义了一组方法（行为），任何类如果声称自己“遵守”这份合同（即实现这个接口），
 * 就必须具体完成合同中规定的所有行为。
 *
 * 学习建议：
 * 1. 理解接口作为“规范”的角色。
 * 2. 注意 `implements` 关键字的用法。
 * 3. 思考接口如何帮助我们实现程序的灵活性和可扩展性。
 */

// ===================================================================
// 1. 什么是接口 (What is an Interface)?
// ===================================================================
// - 接口是一种完全抽象的类型。它只定义方法签名（方法名、参数、返回类型），不提供方法的具体实现。
//   (Java 8 之后，接口可以有默认方法和静态方法，但核心思想仍然是定义规范)
// - 接口像一个约定，规定了实现该接口的类必须具备哪些功能。
// - 类可以实现 (implements) 一个或多个接口。

// ===================================================================
// 2. 为什么使用接口 (Why use Interfaces)?
// ===================================================================
// - **实现抽象**: 隐藏具体实现，只暴露行为规范。
// - **定义规范/合同**: 确保实现类都遵循同样的方法定义，使得不同类可以被统一处理。
// - **实现类似多重继承的效果**: 一个类只能继承一个父类，但可以实现多个接口。
// - **松耦合 (Loose Coupling)**: 代码更多地依赖于接口而不是具体的实现类，降低了模块间的依赖性，更易于维护和扩展。
//   例如，我们可以编写一个方法，它接受任何实现了 `Playable` 接口的对象，而不用关心这个对象具体是 `VideoGame` 还是 `MusicTrack`。

// ===================================================================
// 3. 如何定义接口 (How to define an Interface)
// ===================================================================
// - 使用 `interface` 关键字。
// - 接口中的方法默认是 `public abstract` (公开抽象的)，所以这两个关键字可以省略。
// - 接口中的属性默认是 `public static final` (公开静态常量)，所以这些关键字也可以省略，并且必须在声明时初始化。

interface Playable {
    // 这是一个常量 (public static final String TYPE = "Playable Media";)
    String TYPE = "可播放媒体";

    void play(); // 这是一个抽象方法 (public abstract void play();)
    // 在Java 8之前，接口中只能有抽象方法和常量。

    // Java 8+ 允许接口有默认方法 (default methods)
    default void displayType() {
        System.out.println("这是一个 " + TYPE + "。");
        // 调用私有方法 (Java 9+)
        // privateHelper();
    }

    // Java 8+ 允许接口有静态方法 (static methods)
    static void showInterfaceInfo() {
        System.out.println("Playable 接口定义了可以播放的媒体行为。");
    }

    // Java 9+ 允许接口有私有方法 (private methods) - 主要用于辅助默认方法
    // private void privateHelper() {
    //     System.out.println("这是Playable接口的私有辅助方法。");
    // }
}

interface Recordable {
    void startRecord();
    void stopRecord();
    // 接口也可以继承其他接口
    // interface AdvancedRecordable extends Recordable { void pauseRecord(); }
}


// ===================================================================
// 4. 如何实现接口 (How to implement an Interface)
// ===================================================================
// - 类使用 `implements` 关键字来实现一个或多个接口。
// - 如果一个类实现了一个接口，它必须提供接口中所有抽象方法的具体实现。
// - 如果一个类没有实现接口中的所有抽象方法，那么这个类必须被声明为抽象类。

// 示例 4.A: VideoGame 类实现 Playable 接口
class VideoGame implements Playable {
    String title;
    String genre;

    public VideoGame(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    // 实现 Playable 接口中的 play 方法
    @Override
    public void play() {
        System.out.println("开始玩视频游戏: 《" + title + "》 (类型: " + genre + ")");
    }

    public void saveGame() {
        System.out.println("游戏 《" + title + "》 已保存进度。");
    }
}

// 示例 4.B: MusicTrack 类实现 Playable 接口
class MusicTrack implements Playable {
    String songTitle;
    String artist;

    public MusicTrack(String songTitle, String artist) {
        this.songTitle = songTitle;
        this.artist = artist;
    }

    // 实现 Playable 接口中的 play 方法
    @Override
    public void play() {
        System.out.println("正在播放音乐: " + songTitle + " - " + artist);
    }

    // MusicTrack 也可以有自己的方法
    public void displayLyrics() {
        System.out.println("显示《" + songTitle + "》的歌词...");
    }

    // 它也可以选择覆盖接口中的默认方法
    @Override
    public void displayType() {
        System.out.println("这是一首歌曲，属于 " + Playable.TYPE + "。");
    }
}

// 示例 4.C: SmartSpeaker 类实现 Playable 和 Recordable 两个接口
class SmartSpeaker implements Playable, Recordable {
    String modelName;

    public SmartSpeaker(String modelName) {
        this.modelName = modelName;
    }

    // 实现 Playable 接口的方法
    @Override
    public void play() {
        System.out.println(modelName + " 智能音箱开始播放音乐...");
    }

    // 实现 Recordable 接口的方法
    @Override
    public void startRecord() {
        System.out.println(modelName + " 智能音箱开始录音...");
    }

    @Override
    public void stopRecord() {
        System.out.println(modelName + " 智能音箱停止录音。");
    }

    public void tellJoke() {
        System.out.println(modelName + " 讲了一个笑话。");
    }
}


public class Interfaces_ForBeginners {
    public static void main(String[] args) {
        System.out.println("--- Java 接口 (Interfaces) 演示 ---
");

        // 调用接口的静态方法
        Playable.showInterfaceInfo();
        System.out.println("Playable 接口定义的类型常量: " + Playable.TYPE);

        System.out.println("
--- 创建实现了 Playable 接口的对象 ---");
        VideoGame marioGame = new VideoGame("超级马里奥", "平台跳跃");
        MusicTrack hotelCalifornia = new MusicTrack("Hotel California", "Eagles");

        marioGame.play(); // 调用 VideoGame 自己的 play 实现
        marioGame.displayType(); // 调用 Playable 接口的默认方法
        marioGame.saveGame();

        System.out.println();
        hotelCalifornia.play(); // 调用 MusicTrack 自己的 play 实现
        hotelCalifornia.displayType(); // 调用 MusicTrack 覆盖后的默认方法
        hotelCalifornia.displayLyrics();

        System.out.println("
--- 使用接口引用类型 (实现松耦合) ---");
        // 我们可以使用接口类型来引用实现了该接口的对象
        Playable myPlayableItem;

        myPlayableItem = new VideoGame("塞尔达传说", "动作冒险");
        System.out.print("通过Playable引用调用: ");
        myPlayableItem.play(); // 调用的是 VideoGame 的 play
        myPlayableItem.displayType();
        // myPlayableItem.saveGame(); // 编译错误! Playable接口没有saveGame方法。需要向下转型。
        if (myPlayableItem instanceof VideoGame) {
            ((VideoGame) myPlayableItem).saveGame();
        }


        myPlayableItem = new MusicTrack("稻香", "周杰伦");
        System.out.print("通过Playable引用调用: ");
        myPlayableItem.play(); // 调用的是 MusicTrack 的 play
        myPlayableItem.displayType();

        System.out.println("
--- 演示一个类实现多个接口 ---");
        SmartSpeaker mySpeaker = new SmartSpeaker("小爱同学");
        mySpeaker.play();
        mySpeaker.startRecord();
        mySpeaker.stopRecord();
        mySpeaker.tellJoke();
        mySpeaker.displayType(); // 继承自 Playable 的默认方法

        // 使用不同接口引用同一个对象
        Playable speakerAsPlayable = mySpeaker;
        Recordable speakerAsRecordable = mySpeaker;

        System.out.println("
通过 Playable 接口引用 SmartSpeaker:");
        speakerAsPlayable.play();
        // speakerAsPlayable.startRecord(); // 编译错误，Playable接口没有startRecord方法

        System.out.println("
通过 Recordable 接口引用 SmartSpeaker:");
        speakerAsRecordable.startRecord();
        speakerAsRecordable.stopRecord();
        // speakerAsRecordable.play(); // 编译错误，Recordable接口没有play方法

        System.out.println("
--- 将不同类型的 Playable 对象放入数组 ---");
        Playable[] mediaPlayers = new Playable[3];
        mediaPlayers[0] = new VideoGame("最终幻想VII", "角色扮演");
        mediaPlayers[1] = new MusicTrack(" Bohemian Rhapsody", "Queen");
        mediaPlayers[2] = new SmartSpeaker("天猫精灵");

        for (Playable item : mediaPlayers) {
            System.out.print("播放: ");
            item.play(); // 多态：根据对象的实际类型调用相应的play方法
            item.displayType(); // 调用默认或被覆盖的displayType方法
            System.out.println("---");
        }

        System.out.println("
--- 接口演示结束 ---");
    }
}
