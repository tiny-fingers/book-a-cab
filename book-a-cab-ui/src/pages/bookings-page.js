import React, {useEffect, useState} from "react";
import {useAuth0} from "@auth0/auth0-react";
import {cancelBooking, getAllBookings} from "../services/cap-booking.service";
import {PageLayout} from "../components/page-layout";

const BookingsPage = () => {
    const [bookings, setBookings] = useState([]);
    const {user, getAccessTokenSilently} = useAuth0();
    const fetchData = async () => {
        const accessToken = await getAccessTokenSilently();
        const {data} = await getAllBookings(accessToken)
        console.log(data)
        setBookings([...data]);
        console.log("bookings " + bookings)
    }

    useEffect(() => {
        void fetchData();
    }, []);

    const handleCancelBooking = async (bookingId) => {
        const accessToken = await getAccessTokenSilently();
        await cancelBooking(accessToken, {bookingId: bookingId});
        await fetchData();
    }

    return (<PageLayout>
            <div className="content-layout">
                <h1 id="page-title" className="content__title">
                    Hello, {user.name}
                </h1>
                <div className="bookings-container">
                    {bookings?.map((booking, index) => {
                        return (
                            <>
                                <div className="booking">
                                    <h5 key={index}>{booking.pickupLocation}</h5>
                                    <h5 key={index}>{booking.destination}</h5>
                                    <h5 key={index}>{booking.pickupTime}</h5>
                                    <div className="cancel-button">
                                    <button id={"cancel-booking"} className={"button"} onClick={() => handleCancelBooking(booking.id)}>Cancel ride</button>
                                    </div>
                                </div>
                            </>
                        )
                    })}
                </div>

            </div>
    </PageLayout>);
};

export default BookingsPage;
