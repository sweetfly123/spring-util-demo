package com.example.threadpool.thoughtworks.factory;

import com.example.threadpool.thoughtworks.Compute.service.AbstractTrainService;
import com.example.threadpool.thoughtworks.Compute.service.TrainService;
import com.example.threadpool.thoughtworks.utils.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 功能描述 创建多个compute
 *
 * @author Barret
 * @date 11/22/2020
 */
public class TrainServiceComputeFactory implements TrainComputeFactory {
    public final String DISTANCE_BETWEEN_TOWNS = "distance_between_towns";
    public final String SHORTEST_PATH_BETWEEN_TWO_TOWNS = "shortest_path_between_two_towns";
    public final String NUMBER_TRIPS_WITH_MAX_STOPS = "number_trips_with_max_stops";
    public final String NUMBER_TRIPS_WITH_SPECIAL_STOPS = "number_trips_with_special_stops";
    public final String NUMBER_TRIPS_WITH_MAX_DISTANCE = "number_trips_with_max_distance";

    private final TrainService service;

    public TrainServiceComputeFactory(TrainService service) {
        this.service = service;
    }

    @Override
    public AbstractTrainService createCompute(String input) {
        String[] parts = input.split(";");

        if (parts.length <= 1) {
            throw new IllegalArgumentException("exception!!! please check input args in file.");
        } else {
            String name = parts[0];

            switch (name) {
                case SHORTEST_PATH_BETWEEN_TWO_TOWNS:
                    return createShortestPathBetweenTwoTowns(input);
                case DISTANCE_BETWEEN_TOWNS:
                    return createDistanceBetweenTowns(input);
                case NUMBER_TRIPS_WITH_MAX_STOPS:
                    return createNumberTripsWithMaxStops(input);
                case NUMBER_TRIPS_WITH_SPECIAL_STOPS:
                    return createNumberTripsWithSpecialStops(input);
                case NUMBER_TRIPS_WITH_MAX_DISTANCE:
                    return createNumberTripsWithMaxDistance(input);
                default:
                    throw new NoSuchElementException("exception!!! please check input args in file.");
            }

        }
    }

    private ShortestPathBetweenTwoTowns createShortestPathBetweenTwoTowns(String input) {
        String[] parts = input.split(";");

        if (parts.length != 3) {
            throw new IllegalArgumentException("exception!!! please check input args in file.");
        } else {
            ShortestPathBetweenTwoTowns command = new ShortestPathBetweenTwoTowns(service);
            command.setStart(parts[1]);
            command.setDest(parts[2]);
            return command;
        }
    }

    private DistanceBetweenTowns createDistanceBetweenTowns(String input) {
        String[] parts = input.split(";");

        if (parts.length < 2) {
            throw new IllegalArgumentException("exception!!! please check input args in file.");
        } else {
            DistanceBetweenTowns command = new DistanceBetweenTowns(service);

            String[] townNames = Arrays.copyOfRange(parts, 1, parts.length);
            command.setTownNames(townNames);

            return command;
        }
    }

    private NumberTripsWithMaxStops createNumberTripsWithMaxStops(String input) {
        String[] parts = input.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("exception!!! please check input args in file.");
        } else {
            NumberTripsWithMaxStops command = new NumberTripsWithMaxStops(service);
            try {
                command.setMaxStops(parts[1], parts[2], Integer.parseInt(parts[3]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("exception!!! please check input args in file.");
            }

            return command;
        }
    }

    private NumberTripsWithSpecialStops createNumberTripsWithSpecialStops(String input) {
        String[] parts = input.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("exception!!! please check input args in file.");
        } else {
            NumberTripsWithSpecialStops command = new NumberTripsWithSpecialStops(service);

            try {
                command.setSpecialStops(parts[1], parts[2], Integer.parseInt(parts[3]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("exception!!! please check input args in file.");
            }

            return command;
        }
    }

    private NumberTripsWithMaxDistance createNumberTripsWithMaxDistance(String input) {
        String[] parts = input.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("exception!!! please check input args in file.");
        } else {
            NumberTripsWithMaxDistance command = new NumberTripsWithMaxDistance(service);
            try {
                command.setMaxDistance(parts[1], parts[2], Integer.parseInt(parts[3]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("exception!!! please check input args in file.");
            }
            return command;
        }
    }
}
