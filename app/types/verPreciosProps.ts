interface VerPreciosProps {
    originCity: string;
    destinationCity: string;
    tripType: string;
    departureDate: string;
    arrivalDate: string;
    applyFilter?: (filter: string) => void;
}