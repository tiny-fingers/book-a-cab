import React, {useEffect, useState} from "react";
import {PageLayout} from "../../components/page-layout";
import {getAllBookings} from "../../services/cap-booking.service";
import {useAuth0} from "@auth0/auth0-react";

const BookingsPage = () => {
    const [bookings, setBookings] = useState([]);
    const {getAccessTokenSilently} = useAuth0();
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            const accessToken = await getAccessTokenSilently();
            const {data} = await getAllBookings(accessToken)
            console.log(data)
            setBookings([...data]);
            console.log("bookings " + bookings)
        }
        void fetchData();

    }, [getAccessTokenSilently]);


    return (<PageLayout>
            <div className="content-layout">
                <h1 id="page-title" className="content__title">
                    My bookings
                </h1>
                <div className="bookings-container">

                    {bookings?.map((booking, index) => <h5 key={index}>{booking.pickupTime}</h5>)}
                </div>

            </div>
        </PageLayout>);
};

export default BookingsPage;
