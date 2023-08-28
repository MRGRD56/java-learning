public class Program88 {
    public static void main(String[] args) {
        int[] stations = new int[] {0, 200, 375, 550, 750, 950};
        System.out.println(minStops(stations, 400));
    }

    private static int minStops(int[] stations, int capacity) {
        int stops = 0;

        int kmPassed = 0;
        int previousStation = 0;

        for (int station : stations) {
            int currentKm = station - previousStation;
            int nextKmPassed = kmPassed + currentKm;

            if (nextKmPassed >= capacity) {
                stops++;
                kmPassed = currentKm;
                System.out.println("Stopping at " + previousStation);
                previousStation = station;
                continue;
            }

            kmPassed = nextKmPassed;
            previousStation = station;
        }

        if (kmPassed > 0) {
            stops++;
            System.out.println("Stopping at " + stations[stations.length - 1]);
        }

        return stops;
    }
}
