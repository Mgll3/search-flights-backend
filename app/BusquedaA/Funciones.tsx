

export const calculateDuration = (departureTime: string, arrivalTime: string): number => {
    const [departureHours, departureMinutes] = departureTime.split(':').map(Number);
    const [arrivalHours, arrivalMinutes] = arrivalTime.split(':').map(Number);
    const departureInMinutes = departureHours * 60 + departureMinutes;
    const arrivalInMinutes = arrivalHours * 60 + arrivalMinutes;
    return arrivalInMinutes - departureInMinutes;
};

export const handleSortByPrice = (flights: Flight[]) => {
    const sorted = [...flights].sort((a, b) => a.price - b.price);
    return sorted;
};

export const handleSortByDuration = (flights: Flight[]) => {
    const sorted = [...flights].sort((a, b) => {
        const durationA = calculateDuration(a.departureDate, a.arrivalDate);
        const durationB = calculateDuration(b.departureDate, b.arrivalDate);
        return durationA - durationB;
    });
    return sorted;
};

export const sortByDirectFlight = (flights: Flight[]): Flight[] => {
    return [...flights].sort((a, b) => {
        if (a.kind_fly === 'Directo') return -1;
        if (a.kind_fly === '1 Escala' && b.kind_fly === '2 Escalas') return -1;
        return 1;
    });
};
