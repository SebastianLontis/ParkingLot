package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars() {
        LOG.info("Finding all cars");
        try {
            TypedQuery<Car> query = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = query.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<CarDto> copyCarsToDto(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarDto(car.getId(), car.getLicensePlate(), car.getParkingSpot(), (String) car.getOwnerName()))
                .toList();
    }

    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        LOG.info("createCar");

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user= entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        entityManager.persist(car);
    }

    public CarDto findById(Long id) {
        Car car = entityManager.find(Car.class, id);
        return new CarDto(car.getId(), car.getLicensePlate(), car.getParkingSpot(), (String) car.getOwnerName());
    }

    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
       LOG.info("updateCar");

        Car car = entityManager.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        //remove this car from the old owner
        User oldUser = car.getOwner();
        oldUser.getCars().remove(car);

        //add this car to the new owner
        User newUser = entityManager.find(User.class, userId);
        newUser.getCars().add(car);
        car.setOwner(newUser);
    }
    public void deleteCarsByIds(List<Long> carIds) {
        LOG.info("deleteCarsByIds");

        for (Long carId : carIds) {
            Car car = entityManager.find(Car.class, carId);
            entityManager.remove(car);
        }
    }
}