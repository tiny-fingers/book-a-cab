import React, {useState} from "react";
import {getCurrentAddress} from "../services/geo-address.service";
import {sendEnquiry} from "../services/cap-booking.service";
import {TbCurrentLocation} from "react-icons/tb";

const EnquireForm = (props: {
    prefilled: {
        pickupLocation: string,
        destination: string,
        minSeat: number,
        pickupTime: string,
        price: number,
        distance: number,
    },
    onConfirmed: (data) => void,
}) => {
    const [pickupLocation, setPickupLocation] = useState(
        props.prefilled?.pickupLocation ?? ""
    );
    const [destination, setDestination] = useState(
        props.prefilled?.destination ?? ""
    );
    const [minSeats, setMinSeats] = useState(props.prefilled?.minSeats ?? 0);
    const [pickupTime, setPickupTime] = useState(
        props.prefilled?.pickupTime ?? new Date().toISOString().substring(0, 16)
    );

    const [price, setPrice] = useState(
        props.prefilled?.price ? props.prefilled?.price : null
    );
    const [distance, setDistance] = useState(
        props.prefilled?.distance ? props.prefilled?.distance : null
    );

    const [infoUpdated, setInfoUpdated] = useState(true);

    const [usePrefilled, setUsePrefilled] = useState(!!props.prefilled);

    const getLocation = async () => {
        const {data} = (await getCurrentAddress());
        setPickupLocation(data);
        setInfoUpdated(true);
    };

    async function handleButtonClick() {
        const {data} = await sendEnquiry({
            pickupLocation,
            destination,
            minSeats,
            pickupTime,
        });
        setPrice(data.price);
        setDistance(data.distance);
        setInfoUpdated(false);
    }

    const handleConfirm = () => {
        setInfoUpdated(false);
        const data = {
            pickupLocation: pickupLocation,
            destination: destination,
            price: price,
            minSeats: minSeats,
            distance: distance,
            pickupTime: pickupTime,
        };
        console.log("Form handleConfirm data " + data)
        props.onConfirmed({...data});
    };

    return (
        <>
            <div className="container">
                <div className="container--columns">
                    <div>
                        <h5>Pick-up location: </h5>
                    </div>
                    <div>
                        <div className="input-icons">
                            <i>
                                <TbCurrentLocation onClick={getLocation}/>
                            </i>
                            <input
                                onChange={(event) => {
                                    setInfoUpdated(true);
                                    setUsePrefilled(false);
                                    setPickupLocation(event.target.value);
                                }}
                                className="input-text-with-icon"
                                type="text"
                                value={pickupLocation}
                                placeholder="Location"
                            />
                        </div>
                    </div>
                    <div>
                        <h5>Destination: </h5>
                    </div>
                    <div>
                        <input
                            onChange={(event) => {
                                setInfoUpdated(true);
                                setUsePrefilled(false);
                                setDestination(event.target.value);
                            }}
                            className="input--text"
                            value={destination}
                            type="text"
                            placeholder="Where do you want to go?"
                        />
                    </div>
                    <div>
                        <h5>Pickup time: </h5>
                    </div>
                    <div className="input--pickuptime">
                        <input
                            onChange={(event) => setPickupTime(event.target.value)}
                            type="datetime-local"
                            placeholder={pickupTime}
                            value={pickupTime}
                        />
                    </div>
                    <div>
                        <h5>Minimum seats: </h5>
                    </div>
                    <div className="input--seats">
                        <input
                            value={minSeats}
                            type="number"
                            min={0}
                            onChange={(event) => {
                                setInfoUpdated(true);
                                setUsePrefilled(false);
                                setMinSeats(event.target.value);
                            }}
                            placeholder="4"
                        />
                    </div>
                    <div>
                        <h5>Price: </h5>
                    </div>
                    <div>
                        <h5>{price} Euro</h5>
                    </div>
                    <div>
                        <h5>Distance: </h5>
                    </div>
                    <div>
                        <h5>{distance} km</h5>
                    </div>
                </div>
                {infoUpdated && !usePrefilled && (
                    <div>
                        <button
                            className="enquiry--button button"
                            onClick={handleButtonClick}
                        >
                            Get info
                        </button>
                    </div>
                )}
                {(usePrefilled || !infoUpdated) && (
                    <div>
                        <button
                            className="enquiry--confirm--button button"
                            onClick={handleConfirm}
                        >
                            Confirm
                        </button>
                    </div>
                )}
            </div>
        </>
    );
};

export default EnquireForm;
