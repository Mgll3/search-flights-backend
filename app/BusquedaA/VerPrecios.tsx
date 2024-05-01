/* eslint-disable @next/next/no-page-custom-font */

import React, { useState, useEffect } from 'react';
import type { NextPage } from 'next';
import '../../styles/tailwind.css';

type Filters = {
    directFlight: boolean;
    price: boolean;
    duration: boolean;
};
    
    const VerPrecios: NextPage<VerPreciosProps> = ({ originCity, destinationCity, tripType, departureDate, arrivalDate  }) => {
    const [flights, setFlights] = useState<Flight[]>([]);
    const [showPrices, setShowPrices] = useState(true);
    const [sortedFlights, setSortedFlights] = useState<Flight[]>([]);
    const [filters, setFilters] = useState<Filters>({
        directFlight: false,
        price: false,
        duration: false,
    });
    const [activeFilters, setActiveFilters] = useState<(keyof Filters)[]>([]);
    const baseUrl: string = 'http://localhost:8080'; //Base de la url de la API

    useEffect(() => {
        //Dependiendo del tipo de vuelo hacer la petición correspondiente
        if (tripType == 'oneWay') {
            fetchDataOneWay();
        } else {
            fetchDataTwoWays();
        }
    }, []);

    //Obtener los datos de la Api - Re uso de codigo
    const fetchDataFlights = async (url: string) => {
        
        fetch(url)
            // Recibe la respuesta y la convierte en JSON
            .then(response => response.json())
            // Guardar la respuesta en los States
            .then((jsonData) => {
                setFlights(jsonData);
                setSortedFlights(jsonData);
            })
            //Manejo de errores
            .catch(error => {
                console.error('Hubo un error:', error);
            });
    }

    //Vuelo solo de ida, donde se conecta con los endpoints del backend
    const fetchDataOneWay = async () => {
        //Con esta url se obtienen los datos de los vuelos
        const url: string = `${baseUrl}/flights/v1/searchByDepartureDate?originCity=${originCity}&destinationCity=${destinationCity}&departureDate=${departureDate}`;
        
        //Utilizamos el método fetch para obtener los datos
        fetchDataFlights(url);
        
    };

    // Traer datos de la api en Vuelo ida y vuelta
    const fetchDataTwoWays = async () => {
        //Con esta url se obtienen los datos de los vuelos
        const url: string = `${baseUrl}/flights/v1/searchByDepartureAndArrivalDate?originCity=${originCity}&destinationCity=${destinationCity}&departureDate=${departureDate}&arrivalDate=${arrivalDate}`;
        
        fetchDataFlights(url);
        
    };

    const handleLogoClick = () => {
        setShowPrices(false);
    };

    const handleSortByPrice = () => {
        const sorted = [...flights].sort((a, b) => a.price - b.price);
        setSortedFlights(sorted);
        toggleFilter('price');
    };

    const handleSortByDirectFlight = () => {
        const sorted = [...flights].sort((a, b) => {
            if (a.kind_fly === 'Directo') return -1;
            if (a.kind_fly === '1 Escala' && b.kind_fly === '2 Escalas') return -1;
            return 1;
        });
        setSortedFlights(sorted);
        toggleFilter('directFlight');
    };

    // const handleSortByDuration = () => {
    //     const sorted = [...flights].sort((a, b) => {
    //         const durationA = calculateDuration(a.departureDate, a.arrivalDate);
    //         const durationB = calculateDuration(b.departureDate, b.arrivalDate);
    //         return durationA - durationB;
    //     });
    //     setSortedFlights(sorted);
    //     toggleFilter('duration');
    // };

    // const calculateDuration = (departureTime: string, arrivalTime: string): number => {
    //     const [departureHours, departureMinutes] = departureTime.split(':').map(Number);
    //     const [arrivalHours, arrivalMinutes] = arrivalTime.split(':').map(Number);
    //     const departureInMinutes = departureHours * 60 + departureMinutes;
    //     const arrivalInMinutes = arrivalHours * 60 + arrivalMinutes;
    //     return arrivalInMinutes - departureInMinutes;
    // };

    const toggleFilter = (filter: keyof Filters) => {
        setFilters(prevFilters => ({ ...prevFilters, [filter]: !prevFilters[filter] }));
        setActiveFilters(prevFilters => {
            if (prevFilters.includes(filter)) {
                return prevFilters.filter(activeFilter => activeFilter !== filter);
            } else {
                return [...prevFilters, filter];
            }
        });
    };
    

    const handleActiveFilterClick = (filter: keyof Filters) => {
        toggleFilter(filter); // Esto quita el filtro de los activos
        // Volvemos a aplicar los filtros restantes a los vuelos ordenados
        const remainingFilters = activeFilters.filter(activeFilter => activeFilter !== filter);
        let sorted = [...flights];
        remainingFilters.forEach(activeFilter => {
            switch (activeFilter) {
                case 'price':
                    sorted = sorted.sort((a, b) => a.price - b.price);
                    break;
                case 'directFlight':
                    sorted = sorted.sort((a, b) => {
                        if (a.kind_fly === 'Directo') return -1;
                        if (a.kind_fly === '1 Escala' && b.kind_fly === '2 Escalas') return -1;
                        return 1;
                    });
                    break;
                case 'duration':
                    // sorted = sorted.sort((a, b) => {
                    //     const durationA = calculateDuration(a.departureDate, a.arrivalDate);
                    //     const durationB = calculateDuration(b.departureDate, b.arrivalDate);
                    //     return durationA - durationB;
                    // });
                    // break;
                default:
                    break;
            }
        });
        setSortedFlights(sorted);
    };

    
    return (
        <div>
            <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&display=optional" />
            {showPrices && (
                <div className="flight-header-container">
                    <div className="flight-header">
                        <div className="special-logo-container">
                            <div className="flight-header-filter">
                                <span className="material-symbols-outlined">flight_takeoff</span>
                            </div>
                            <div className="flight-header-text">
                                Viaje de {originCity} a {destinationCity} - Dom, Mar 14, 2024
                            </div>
                        </div>
                        <div className="flight-header-filter">
                            <span className="material-symbols-outlined">filter_alt</span>
                            <span>Recomendado:</span>
                            <button className={`filter-button ${filters.directFlight ? 'active' : ''}`} onClick={handleSortByDirectFlight}>
                                Vuelo Directo
                            </button>
                            <button className={`filter-button ${filters.price ? 'active' : ''}`} onClick={handleSortByPrice}>
                                Mejor Precio
                            </button>
                            {/* <button className={`filter-button ${filters.duration ? 'active' : ''}`} onClick={handleSortByDuration}>
                            </button> */}
                        </div>
                    </div>
                </div>
            )}

            <div className="active-filters-header">
            <span className="active-filters-title">Filtros Aplicados:</span>
            {activeFilters.map((filter, index) => (
                <div key={index} className="active-filter" onClick={() => handleActiveFilterClick(filter)}>
                {filter === 'directFlight' && 'Vuelo Directo'}
                {filter === 'price' && 'Mejor Precio'}
                {filter === 'duration' && 'Duración'}
                </div>
            ))}
            </div>

            <div className="container">
                {sortedFlights.map((flight, index) => (
                    <div key={index} className="flight-option" style={{ marginBottom: '20px' }}>
                        <div className="best-price">MEJOR PRECIO</div>
                        <div>
                            <div className="departure-time">{flight.departureDate}</div>
                            <div className="airport-codes">{originCity}</div>
                        </div>
                        <div className="flight-separator">
                            <div className="flight-separator.left"></div>
                        </div>
                        <div className="flex items-center justify-center">
                            <div>
                                <div>{flight.kind_fly}</div>
                            </div>
                            <span className="material-symbols-outlined">flight_takeoff</span>
                        </div>
                        <div className="flight-separator">
                            <div className="flight-separator.left"></div>
                        </div>
                        <div>
                            <div className="arrival-time">{flight.arrivalDate}</div>
                            <div className="airport-codes">{destinationCity}</div>
                        </div>
                        <div>
                            <div className="price">
                                <div className="left-border"></div>
                                {flight.price} COP
                            </div>
                        </div>
                    </div>
                ))}
            </div>

            <div style={{ height: '50px' }} />
        </div>
    );
}

export default VerPrecios;
