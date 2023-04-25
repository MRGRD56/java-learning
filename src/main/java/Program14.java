public class Program14 {
    public static void main(String[] args) {

    }

    public static class AudioPlayerAdapter implements MediaPlayer {
        private final MediaPlayer mediaPlayer;

        public AudioPlayerAdapter(Object player) {
            if (player instanceof Mp3Player mp3Player) {
                this.mediaPlayer = new Mp3PlayerAdapter(mp3Player);
            } else if (player instanceof FlacPlayer flacPlayer) {
                this.mediaPlayer = new FlacPlayerAdapter(flacPlayer);
            } else if (player instanceof WavPlayer wavPlayer) {
                this.mediaPlayer = new WavPlayerAdapter(wavPlayer);
            } else {
                throw new IllegalArgumentException("Player has invalid type");
            }
        }

        @Override
        public void play(String fileName) {
            mediaPlayer.play(fileName);
        }
    }

    private static class Mp3PlayerAdapter implements MediaPlayer {
        private final Mp3Player player;

        public Mp3PlayerAdapter(Mp3Player player) {
            this.player = player;
        }

        @Override
        public void play(String fileName) {
            player.playMp3(fileName);
        }
    }

    private static class WavPlayerAdapter implements MediaPlayer {
        private final WavPlayer player;

        public WavPlayerAdapter(WavPlayer player) {
            this.player = player;
        }

        @Override
        public void play(String fileName) {
            player.playWavFile(fileName);
        }
    }

    private static class FlacPlayerAdapter implements MediaPlayer {
        private final FlacPlayer player;

        public FlacPlayerAdapter(FlacPlayer player) {
            this.player = player;
        }

        @Override
        public void play(String fileName) {
            player.playFlac(fileName);
        }
    }

    public interface MediaPlayer {
        void play(String fileName);
    }

    public static class Mp3Player {
        public void playMp3(String fileName) {
            // Play MP3 audio
        }
    }

    public static class WavPlayer {
        public void playWavFile(String file) {
            // Play WAV audio
        }
    }

    public static class FlacPlayer {
        public void playFlac(String filePath) {
            // Play FLAC audio
        }
    }
}
